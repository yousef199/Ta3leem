package com.yousef.ta3leem.Kotlin.ui.Channel

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yousef.ta3leem.Constants
import com.yousef.ta3leem.R
import com.yousef.ta3leem.databinding.FragmentChannelBinding
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.livedata.ChatDomain
import io.getstream.chat.android.ui.avatar.AvatarView
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel
import io.getstream.chat.android.ui.channel.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

class ChannelFragment : Fragment() {
    private val args: ChannelFragmentArgs by navArgs()

    private var _binding: FragmentChannelBinding? = null
    private val binding get() = _binding!!

    private val client = ChatClient.instance()
    private lateinit var user: User

    lateinit var sharedPreferences:SharedPreferences


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChannelBinding.inflate(inflater, container, false)
        val login = args.login
        var id: String? = ""
        var name:String? = ""
        var image:String? = ""

        if(login.equals("student")){
            sharedPreferences = requireActivity().getSharedPreferences(Constants.STUDENT_SHARED_PREF , Context.MODE_PRIVATE);
             id  = sharedPreferences.getString("id" , null)
             name  = sharedPreferences.getString("name" , null);
             image  = sharedPreferences.getString("image" , null);
        }
        else{
            sharedPreferences = requireActivity().getSharedPreferences(Constants.TEACHER_SHARED_PREF , Context.MODE_PRIVATE);
            id  = sharedPreferences.getString("id" , null)
            name  = sharedPreferences.getString("name" , null);
            image  = sharedPreferences.getString("image" , null);
        }

        setupUser(id , name , image)
        setupChannels()
        setupDrawer(login)

        binding.channelsView.setChannelDeleteClickListener { channel ->
            deleteChannel(channel)
        }

        binding.channelListHeaderView.setOnActionButtonClickListener {
            findNavController().navigate(R.id.action_channelFragment_to_usersFragment)
        }

        binding.channelsView.setChannelItemClickListener { channel ->
            val action = ChannelFragmentDirections.actionChannelFragmentToChatFragment(channel.cid)
            findNavController().navigate(action)
        }

        binding.channelListHeaderView.setOnUserAvatarClickListener {
            binding.drawerLayout.openDrawer(Gravity.END)
        }

        return binding.root
    }

    private fun setupUser(id:String? , name:String? , image:String?) {
        if(id != null && image != null && name != null) {
            if (client.getCurrentUser() == null) {
                user = User(
                        id =id,
                        extraData = mutableMapOf(
                            "name" to name,
                            "country" to "Jordan",
                            "image" to image
                        )
                    )
                }}

                val token = client.devToken(user.id)
                client.connectUser(
                    user = user,
                    token = token
                ).enqueue { result ->
                    if (result.isSuccess) {
                        Log.d("ChannelFragment", "Success Connecting the User")
                    } else {
                        Log.d("ChannelFragment", result.error().message.toString())
                    }
                }
            }



    private fun setupChannels() {
        val filters = Filters.and(
            Filters.eq("type", "messaging"),
            Filters.`in`("members", listOf(client.getCurrentUser()!!.id))
        )
        val viewModelFactory = ChannelListViewModelFactory(
            filters,
            ChannelListViewModel.DEFAULT_SORT
        )
        val listViewModel: ChannelListViewModel by viewModels { viewModelFactory }
        val listHeaderViewModel: ChannelListHeaderViewModel by viewModels()

        listHeaderViewModel.bindView(binding.channelListHeaderView, viewLifecycleOwner)
        listViewModel.bindView(binding.channelsView, viewLifecycleOwner)
    }

    private fun setupDrawer(login:String?) {
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.logout_menu) {
                logout(login)
            }
            false
        }
        val currentUser = client.getCurrentUser()!!
        val headerView = binding.navigationView.getHeaderView(0)
        val headerAvatar = headerView.findViewById<AvatarView>(R.id.avatarView)
        headerAvatar.setUserData(currentUser)
        val headerId = headerView.findViewById<TextView>(R.id.id_textView)
        headerId.text = currentUser.id
        val headerName = headerView.findViewById<TextView>(R.id.name_textView)
        headerName.text = currentUser.name
    }

    private fun deleteChannel(channel: Channel) {
        ChatDomain.instance().deleteChannel(channel.cid).enqueue { result ->
            if (result.isSuccess) {
                showToast("Channel: ${channel.name} removed!")
            } else {
                Log.e("ChannelFragment", result.error().message.toString())
            }
        }
    }

    private fun logout(login: String?) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("نعم") { _, _ ->
            client.disconnect()
            if(login.equals("student")){
                findNavController().navigate(R.id.action_channelFragment_to_studentMainPage)
            }
            else
                findNavController().navigate(R.id.action_channelFragment_to_teacherMainPageFragment)
            showToast("تم العوده بتجاح")
        }
        builder.setNegativeButton("لا") { _, _ -> }
        builder.setTitle("الخروج")
        builder.setMessage("هل متاكد انك تريد العوده للصفحه الرئيسيه؟")
        builder.create().show()
    }

    private fun showToast(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}









