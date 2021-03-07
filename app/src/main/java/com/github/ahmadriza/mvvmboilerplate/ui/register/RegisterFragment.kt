package com.github.ahmadriza.mvvmboilerplate.ui.register

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentRegisterBinding
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.github.ahmadriza.mvvmboilerplate.utils.disable
import com.github.ahmadriza.mvvmboilerplate.utils.enable
import com.github.ahmadriza.mvvmboilerplate.utils.gone
import com.github.ahmadriza.mvvmboilerplate.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private var isMen = true
    private val vm: RegisterVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_register

    override fun initViews() {

        binding.tvGender.setOnClickListener { selectGender() }
        binding.btnRegister.setOnClickListener { submit() }
    }

    override fun initObservers() {
        vm.response.observe(viewLifecycleOwner) {

            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.loading.visible()
                    binding.btnRegister.disable()
                }
                else -> {
                    binding.loading.gone()
                    binding.btnRegister.enable()
                    if (it.status == Resource.Status.SUCCESS) {
                        findNavController().popBackStack()
                    } else {
                        context?.toast(it.message.toString())
                    }
                }
            }

        }
    }

    override fun initData() {

    }

    private fun submit() {

        val name = binding.etName.text.toString().ifBlank {
            context?.toast("Nama tidak boleh kosong")
            return
        }

        val age = binding.etAge.text.toString().ifBlank {
            context?.toast("Umur tidak boleh kosong")
            return
        }

        val address = binding.etAddress.text.toString().ifBlank {
            context?.toast("Alamat tidak boleh kosong")
            return
        }

        val email = binding.etEmail.text.toString().ifBlank {
            context?.toast("Email tidak boleh kosong")
            return
        }

        val password1 = binding.etPass1.text.toString().ifBlank {
            context?.toast("Password tidak boleh kosong")
            return
        }

        val password2 = binding.etPass2.text.toString().ifBlank {
            context?.toast("Konfirmasi password tidak boleh kosong")
            return
        }

        if (password1 != password2) {
            context?.toast("Password harus sama")
            return
        }

        val phone = binding.etPhone.text.toString().ifBlank {
            context?.toast("Nomor hp tidak boleh kosong")
            return
        }

        vm.register(
            name = name,
            email = email,
            password = password1, phone = phone, age = age.toInt(), address = address, isMen = isMen
        )

    }

    private fun selectGender() {

        context?.selector(
            title = null,
            listOf(
                "Pria (Tn.)", "Wanita (Ny.)"
            )
        ) { d, i ->
            isMen = i == 0
            binding.tvGender.text = if (isMen) "Tn." else "Ny."
            d.dismiss()
        }

    }

}