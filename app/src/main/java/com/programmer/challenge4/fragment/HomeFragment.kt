package com.programmer.challenge4.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.programmer.challenge4.R
import com.programmer.challenge4.adapter.MenuAdapter
import com.programmer.challenge4.databinding.FragmentHomeBinding
import com.programmer.challenge4.item.MenuItem

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MenuAdapter
    private lateinit var sharedPrefs: SharedPreferences
    private val PREF_NAME = "MyPrefs"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupMenu()

        sharedPrefs = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val layoutManagerGrid = GridLayoutManager(requireContext(), 2)
        val layoutManagerLinear = LinearLayoutManager(requireContext())

        //Inisialisasi layout manager saat membuka aplikasi
        val savedLayout = sharedPrefs.getString("layout", "grid") ?: "grid"

        var currentLayoutManager = if (savedLayout == "grid") {
            binding.btnList.setImageResource(R.drawable.baseline_grid_view_24)
            layoutManagerGrid
        } else {
            binding.btnList.setImageResource(R.drawable.list)
            layoutManagerLinear
        }

        binding.rvMenuMakanan.layoutManager = currentLayoutManager

        binding.btnList.setOnClickListener {
            currentLayoutManager = if (currentLayoutManager == layoutManagerGrid) {
                binding.btnList.setImageResource(R.drawable.list)
                sharedPrefs.edit().putString("layout", "linear").apply()
                layoutManagerLinear
            } else {
                binding.btnList.setImageResource(R.drawable.baseline_grid_view_24)
                sharedPrefs.edit().putString("layout", "grid").apply()
                layoutManagerGrid
            }
            binding.rvMenuMakanan.layoutManager = currentLayoutManager
        }

        return binding.root
    }

    private fun setupMenu() {
        val menuItems = getMenuItems()
        adapter = MenuAdapter(menuItems) { selectedItem ->
            val bundle = Bundle().apply {
                putString("name", selectedItem.name)
                putInt("price", selectedItem.price)
                putString("description", selectedItem.description)
                putInt("imageRes", selectedItem.imageRes)
                putString("restaurantAddress", selectedItem.restaurantAddress)
                putString("googleMapsUrl", selectedItem.googleMapsUrl)
            }
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.detailActivity, bundle)
        }
        binding.rvMenuMakanan.adapter = adapter
    }

    private fun getMenuItems(): List<MenuItem> {
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(MenuItem(
            "Dimsum",
            20000,
            "Dimsum makanan paling dicari semua orang mau anak-anak, remaja, hingga dewasa!",
            R.drawable.dimsum,
            "Bandung",
            "https://maps.app.goo.gl/Kd1hbopN2DhnY4DQ9"
        ))

        menuItems.add(MenuItem(
            "Kentang",
            20000,
            "Kentang Goreng pakai saus tomat",
            R.drawable.kentang,
            "Jakarta",
            "https://maps.app.goo.gl/2yxKvmefahrrB9u19"
        ))

        menuItems.add(MenuItem(
            "Burger",
            15000,
            "Burger big dengan varian baru dibalut saus keju",
            R.drawable.burger,
            "Depok",
            "https://maps.app.goo.gl/eQdfDWJbbu2GFCMm9"
        ))

        menuItems.add(MenuItem(
            "Sushi",
            35000,
            "Sushi makanan favorit anak jaman now",
            R.drawable.sushi,
            "Surabaya",
            "https://maps.app.goo.gl/5pN2U8kR9y3QDjBE6"
        ))

        menuItems.add(MenuItem(
            "Strawberry Milk",
            10000,
            "Minuman segar dikolaborasi dengan susu sapi australia",
            R.drawable.strawberry_milk,
            "Bogor",
            "https://maps.app.goo.gl/vNGTFJRV1FFeVko1A"
        ))

        menuItems.add(MenuItem(
            "Chicken",
            25000,
            "Chicken dengan sambal ijo",
            R.drawable.chicken,
            "Garut",
            "https://maps.app.goo.gl/cMSAqxLjtVUTNP2Z9"
        ))
        menuItems.add(MenuItem(
            "Dimsum",
            25000,
            "Dimsum makanan paling dicari semua orang mau anak-anak, remaja, hingga dewasa!",
            R.drawable.dimsum,
            "Bandung",
            "https://maps.app.goo.gl/Kd1hbopN2DhnY4DQ9"
        ))

        menuItems.add(MenuItem(
            "Kentang",
            20000,
            "Kentang Goreng pakai saus tomat",
            R.drawable.kentang,
            "Jakarta",
            "https://maps.app.goo.gl/2yxKvmefahrrB9u19"
        ))

        menuItems.add(MenuItem(
            "Burger",
            15000,
            "Burger big dengan varian baru dibalut saus keju",
            R.drawable.burger,
            "Depok",
            "https://maps.app.goo.gl/eQdfDWJbbu2GFCMm9"
        ))

        menuItems.add(MenuItem(
            "Sushi",
            35000,
            "Sushi makanan favorit anak jaman now",
            R.drawable.sushi,
            "Surabaya",
            "https://maps.app.goo.gl/5pN2U8kR9y3QDjBE6"
        ))

        return menuItems
    }
}
