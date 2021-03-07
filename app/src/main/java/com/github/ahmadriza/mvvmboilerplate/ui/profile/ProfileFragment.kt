package com.github.ahmadriza.mvvmboilerplate.ui.profile

import androidx.fragment.app.viewModels
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentProfileBinding
import com.github.ahmadriza.mvvmboilerplate.ui.main.MainActivity
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val vm: ProfileVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_profile

    override fun initViews() {
        binding.btnLogOut.setOnClickListener { vm.logOut() }
    }

    override fun initObservers() {
        vm.isLoggedOut.observe(viewLifecycleOwner) {
            startActivity(context?.intentFor<MainActivity>()?.clearTop()?.clearTask())
        }
    }

    override fun initData() {

    }

}