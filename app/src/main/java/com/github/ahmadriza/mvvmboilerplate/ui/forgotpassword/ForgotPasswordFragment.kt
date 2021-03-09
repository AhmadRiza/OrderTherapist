package com.github.ahmadriza.mvvmboilerplate.ui.forgotpassword

import android.content.Intent
import androidx.fragment.app.viewModels
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentForgotPasswordBinding
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.github.ahmadriza.mvvmboilerplate.utils.gone
import com.github.ahmadriza.mvvmboilerplate.utils.visibleOrGone
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

    private val vm: ForgotPasswordVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_forgot_password

    override fun initViews() {
        binding.btnSend.setOnClickListener {

            if (binding.etEmail.text.isNullOrBlank()) {
                context?.toast("Isi email terlebih dahulu")
                return@setOnClickListener
            }

            vm.submit(binding.etEmail.text.toString())

        }
    }

    override fun initObservers() {

        vm.response.observe(viewLifecycleOwner) {

            binding.loading.visibleOrGone(it.status == Resource.Status.LOADING)
            binding.btnSend.isEnabled = it.status != Resource.Status.LOADING

            when (it.status) {
                Resource.Status.SUCCESS -> {

                    binding.tvStatus.text = "Email instruksi terkirim"
                    binding.tvStatusInfo.text =
                        "Instruksi telah terkirim ke ${binding.etEmail.text}. Silahkan buka kotak masuk email (termasuk di kotak spam) dan ikuti petujuk yang telah kami kirim"
                    binding.etEmail.gone()
                    binding.btnSend.text = "Buka Email"
                    binding.btnSend.setOnClickListener {
                        openEmailClient()
                    }
                }

                Resource.Status.ERROR -> {
                    context?.toast(it.message.toString())
                }

                else -> {
                }

            }
        }

    }

    override fun initData() = Unit

    private fun openEmailClient() {
        val intent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_EMAIL)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //Min SDK 15

        startActivity(intent)
    }
}