package com.misRegistros.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.misRegistros.fragments.*

class MyViewPagerAdapterPersona(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return GastoPersonalFragment()

            }
            1 -> {
                return IngresoPersonalFragment()

            }
            else -> { return GastoPersonalFragment()

            }
        }
    }
}