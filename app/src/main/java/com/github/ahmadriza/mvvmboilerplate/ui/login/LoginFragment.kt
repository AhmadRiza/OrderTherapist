package com.github.ahmadriza.mvvmboilerplate.ui.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentLoginBinding
import com.github.ahmadriza.mvvmboilerplate.ui.main.MainActivity
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.github.ahmadriza.mvvmboilerplate.utils.disable
import com.github.ahmadriza.mvvmboilerplate.utils.enable
import com.github.ahmadriza.mvvmboilerplate.utils.gone
import com.github.ahmadriza.mvvmboilerplate.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val vm: LoginVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_login

    override fun initViews() {

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnSignIn.setOnClickListener {
            vm.login(
                email = binding.etEmail.text?.toString() ?: "",
                password = binding.etEmail.text?.toString() ?: ""
            )
        }

    }

    override fun initObservers() {
        vm.response.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.loading.visible()
                    binding.btnSignIn.disable()
                }
                else -> {
                    binding.loading.gone()
                    binding.btnSignIn.enable()

                    if (it.status == Resource.Status.SUCCESS) {
                        startActivity(
                            context?.intentFor<MainActivity>()?.clearTop()?.clearTask()
                        )
                    } else {
                        context?.toast(it.message.toString())
                    }

                }
            }

        }

        vm.user.observe(viewLifecycleOwner) {
            it?.email?.let {
                binding.etEmail.setText(it)
            }
        }


    }

    override fun initData() {

    }


}