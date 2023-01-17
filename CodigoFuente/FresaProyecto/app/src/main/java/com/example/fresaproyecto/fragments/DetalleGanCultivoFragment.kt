package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.*
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetalleGanCultivoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetalleGanCultivoFragment : Fragment() {
    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var tab_layout: TabLayout
    lateinit var view_pager: ViewPager2
    lateinit var myViewPagerAdapter : MyViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            actividad = context
            InformeCultivoFragment.interfaceComunicaFragments = actividad as IComunicaFragments
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_detalle_gan_cultivo, container, false)


        view_pager = vista.findViewById(R.id.view_pager)
        tab_layout = vista.findViewById(R.id.tab_layout)
        myViewPagerAdapter = MyViewPagerAdapter(this)
        view_pager.setAdapter(myViewPagerAdapter)

        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.setCurrentItem(tab!!.position)
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tab_layout.getTabAt(position)?.select()
            }
        })
        return vista
    }


    private fun reemplazarFragment(fragment: Fragment){
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction!!.replace(R.id.frame_container, fragment)
        fragmentTransaction.commit()
    }





    companion object {




        lateinit var interfaceComunicaFragments: IComunicaFragments
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetalleGanCultivoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetalleGanCultivoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}