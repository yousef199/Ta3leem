package com.yousef.ta3leem.ui.UI.Teacher.Fragments

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.MimeTypeMap
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yousef.ta3leem.R
import com.yousef.ta3leem.databinding.StudentclassmainpageBinding
import com.yousef.ta3leem.databinding.TeacherclassmainpageBinding

class TeacherClassMainPage : Fragment() {
    private var _binding: TeacherclassmainpageBinding? = null
    private val binding get() = _binding!!
    private var isOpen = false
    lateinit var uri:Uri
    private val  fab_close:Animation by lazy { AnimationUtils.loadAnimation(activity , R.anim.fab_close) }
    private val  fab_open:Animation by lazy { AnimationUtils.loadAnimation(activity , R.anim.fab_open) }
    private val  rotate_backward:Animation by lazy { AnimationUtils.loadAnimation(activity , R.anim.rotate_backward) }
    private val  rotate_forward:Animation by lazy { AnimationUtils.loadAnimation(activity , R.anim.rotate_forward) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TeacherclassmainpageBinding.inflate(inflater , container , false);

        binding.teacherClassPageBottomNav.background = null
        binding.teacherClassPageBottomNav.itemIconTintList = null
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpNavController()
        binding.teacherClassPageFAB.setOnClickListener{
            animateFAB()
        }
    }

    fun animateFAB(){
        if(isOpen){
            binding.classMainPageExamText.visibility = View.INVISIBLE
            binding.classMainPageHWText.visibility = View.INVISIBLE
            binding.classMainPageLectureText.visibility = View.INVISIBLE
            binding.teacherClassPageFABAddVideo.visibility = View.INVISIBLE
            binding.teacherClassPageFABAddHW.visibility = View.INVISIBLE
            binding.classMainPageExamText.startAnimation(fab_close)
            binding.teacherClassPageFABAddExam.startAnimation(fab_close)
            binding.classMainPageHWText.startAnimation(fab_close)
            binding.classMainPageLectureText.startAnimation(fab_close)
            binding.teacherClassPageFABAddVideo.startAnimation(fab_close)
            binding.teacherClassPageFABAddHW.startAnimation(fab_close)
            binding.teacherClassPageFABAddExam.isClickable = false
            binding.teacherClassPageFABAddHW.isClickable = false
            binding.teacherClassPageFABAddVideo.isClickable = false
            isOpen = false
        }
        else{
            binding.classMainPageExamText.visibility = View.VISIBLE
            binding.classMainPageHWText.visibility = View.VISIBLE
            binding.classMainPageLectureText.visibility = View.VISIBLE
            binding.teacherClassPageFABAddVideo.visibility = View.VISIBLE
            binding.teacherClassPageFABAddHW.visibility = View.VISIBLE
            binding.classMainPageExamText.startAnimation(fab_open)
            binding.teacherClassPageFABAddExam.startAnimation(fab_open)
            binding.classMainPageHWText.startAnimation(fab_open)
            binding.classMainPageLectureText.startAnimation(fab_open)
            binding.teacherClassPageFABAddVideo.startAnimation(fab_open)
            binding.teacherClassPageFABAddHW.startAnimation(fab_open)
            binding.teacherClassPageFABAddExam.isClickable = true
            binding.teacherClassPageFABAddHW.isClickable = true
            binding.teacherClassPageFABAddVideo.isClickable = true
            isOpen = true
        }
    }

    fun setUpNavController(){
        val navHostFragment = activity?.findNavController(R.id.teacherClassPageNavContainer)

        if(navHostFragment != null){
            binding.teacherClassPageBottomNav.setupWithNavController(navHostFragment)
        }
    }

    fun selectVideo(view: View){
      var intent = Intent()
      intent.setType("video/*")
      intent.setAction(Intent.ACTION_GET_CONTENT)
      startActivityForResult(intent , 101)
    }

    fun getExtension(uri:Uri):String?{
        var contentResolver = activity?.contentResolver
        var mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver?.getType(uri))
    }
}