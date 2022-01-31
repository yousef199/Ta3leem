package com.yousef.ta3leem.ui.UI.Student.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yousef.ta3leem.R
import com.yousef.ta3leem.databinding.FragmentChannelBinding
import com.yousef.ta3leem.databinding.StudentclassmainpageBinding

/**
 * This class sets up the bottom navigation view for each subject page with the student_nav graph
 */
class StudentClassPage : Fragment() {
    private var _binding: StudentclassmainpageBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StudentclassmainpageBinding.inflate(inflater , container , false);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHostFragment = activity?.findNavController(R.id.studentSubjectsPageNavContainer)

        if(navHostFragment != null){
            binding.bottomNavigationView.setupWithNavController(navHostFragment)
        }
    }
}