package com.example.streetworkout.ui.user

import android.content.Context
import androidx.navigation.fragment.findNavController
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseFragment
import com.example.streetworkout.ui.BottomNavigationListener
import kotlinx.android.synthetic.main.fragment_user_information.*

class UserInformationFragment : BaseFragment() {
    private var bottomNavigationListener: BottomNavigationListener? = null

    override val layoutResources: Int
        get() = R.layout.fragment_user_information

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationListener) bottomNavigationListener = context
    }

    override fun initData() {}

    override fun initAction() {
        buttonStart.setOnClickListener { openHomeFragment() }
    }

    private fun openHomeFragment() {
        findNavController().navigate(UserInformationFragmentDirections.actionToNavigationHome())
        bottomNavigationListener?.showBottomNav()
    }
}
