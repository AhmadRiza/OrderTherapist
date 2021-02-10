package com.github.ahmadriza.mvvmboilerplate.ui.login

import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentLoginBinding
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun getLayoutResource(): Int = R.layout.fragment_login

    override fun initViews() {

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    override fun initObservers() {

    }

    override fun initData() {

    }


}