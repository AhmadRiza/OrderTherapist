package com.github.ahmadriza.mvvmboilerplate.ui.profile

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentProfileBinding
import com.github.ahmadriza.mvvmboilerplate.models.MenuItem
import com.github.ahmadriza.mvvmboilerplate.ui.main.MainActivity
import com.github.ahmadriza.mvvmboilerplate.ui.register.RegisterFragment
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import com.github.ahmadriza.mvvmboilerplate.utils.data.Resource
import com.github.ahmadriza.mvvmboilerplate.utils.formatCurrency
import com.github.ahmadriza.mvvmboilerplate.utils.loadRoundImage
import com.github.ahmadriza.mvvmboilerplate.utils.visibleOrGone
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.alert
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val vm: ProfileVM by viewModels()
    private val menuAdapter by lazy { MenuAdapter() }

    override fun getLayoutResource(): Int = R.layout.fragment_profile

    override fun initViews() {
        binding.rvSetting.adapter = menuAdapter

        binding.btnLogOut.setOnClickListener {
            context?.alert {
                message = "Anda yakin ingin keluar?"
                positiveButton("Keluar") {
                    vm.logOut()
                }
                negativeButton("Batal") {
                    it.dismiss()
                }
            }?.show()

        }
        binding.btnTopUp.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_topUpFragment)
        }
    }

    override fun initObservers() {
        vm.isLoggedOut.observe(viewLifecycleOwner) {
            binding.loading.visibleOrGone(it.status == Resource.Status.LOADING)
            binding.btnLogOut.isEnabled = it.status != Resource.Status.LOADING
            if (it.status != Resource.Status.LOADING) startActivity(
                context?.intentFor<MainActivity>()?.clearTop()?.clearTask()
            )
        }
        vm.user.observe(viewLifecycleOwner) {
            it.data?.let {
                binding.tvName.text = it.name
                binding.tvEmail.text = it.email
                binding.tvBalance.text = it.balance.formatCurrency()
                binding.imgProfile.apply {
                    if (it.gender == "laki-laki") loadRoundImage(R.drawable.men)
                    else loadRoundImage(R.drawable.women)
                }
            }

        }
    }

    override fun initData() {
        menuAdapter.submitList(menu)
    }

    override fun onResume() {
        super.onResume()
        vm.refreshUser()
    }

    val menu = listOf(
        MenuItem("Ubah Profil") {
            findNavController().navigate(
                R.id.action_navigation_profile_to_editProfileFragment, bundleOf(
                    Pair(RegisterFragment.EXTRA_EDIT, true)
                )
            )
        }
    )

}