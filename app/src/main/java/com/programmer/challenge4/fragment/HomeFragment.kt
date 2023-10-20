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
import androidx.recyclerview.widget.RecyclerView
import com.programmer.challenge4.R
import com.programmer.challenge4.adapter.MenuAdapter
import com.programmer.challenge4.databinding.FragmentHomeBinding
import com.programmer.challenge4.item.MenuItem

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPrefs: SharedPreferences
    private val PREFS_NAME = "my_shared_prefs"
    private lateinit var layoutManagerGrid: GridLayoutManager
    private lateinit var layoutManagerLinear: LinearLayoutManager
    private lateinit var currentLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inisialisasi SharedPreferences
        sharedPrefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Inisialisasi LayoutManager untuk Grid dan Linear
        layoutManagerGrid = GridLayoutManager(requireContext(), 2)
        layoutManagerLinear = LinearLayoutManager(requireContext())

        // Inisialisasi LayoutManager saat aplikasi pertama kali dibuka
        val savedLayout = sharedPrefs.getString("layout", "grid") // "grid" adalah nilai default jika tidak ada yang tersimpan

        if (savedLayout == "grid") {
            currentLayoutManager = layoutManagerGrid
            binding.btnList.setImageResource(R.drawable.baseline_grid_view_24)
        } else {
            currentLayoutManager = layoutManagerLinear
            binding.btnList.setImageResource(R.drawable.list)
        }

        binding.rvMenuMakanan.layoutManager = currentLayoutManager

        binding.btnList.setOnClickListener {
            // Mengubah tata letak saat tombol diklik
            if (currentLayoutManager == layoutManagerGrid) {
                currentLayoutManager = layoutManagerLinear
                binding.btnList.setImageResource(R.drawable.list)
                sharedPrefs.edit().putString("layout", "linear").apply()
            } else {
                currentLayoutManager = layoutManagerGrid
                binding.btnList.setImageResource(R.drawable.baseline_grid_view_24)
                sharedPrefs.edit().putString("layout", "grid").apply()
            }
            binding.rvMenuMakanan.layoutManager = currentLayoutManager
        }

        setupMenu()

        return view
    }

    private fun setupMenu() {
        // Inisialisasi data menu makanan Anda
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(
            MenuItem(
                "Kentang Goreng",
                "Rp 20.000",
                "Kentang Goreng pakai saus tomat",
                R.drawable.kentang,
                "Alamat Restoran 1",
                "https://maps.app.google.gl/Kd1hbopN2DhnY4DQ9"
            )
        )

        menuItems.add(
            MenuItem(
                "Chicken",
                "Rp 20.000",
                "Chicken dengan sambal ijo",
                R.drawable.chicken,
                "Alamat Restoran 2",
                "https://maps.app.google.gl/Kd1hbopN2DhnY4DQ9"
            )
        )

        menuItems.add(
            MenuItem(
                "Sushi",
                "Rp 35.000",
                "Sushi makanan favorit anak jaman now",
                R.drawable.sushi,
                "Alamat Restoran 3",
                "https://maps.app.google.gl/Kd1hbopN2DhnY4DQ9"
            )
        )

        menuItems.add(
            MenuItem(
                "Dimsum",
                "Rp 25.000",
                "Dimsum makanan paling dicari semua orang mau anak-anak, remaja, hingga dewasa",
                R.drawable.dimsum,
                "Alamat Restoran 4",
                "https://maps.app.google.gl/Kd1hbopN2DhnY4DQ9"
            )
        )

        menuItems.add(
            MenuItem(
                "Burger",
                "Rp 25.000",
                "Burger big dengan varian baru dibalut saus keju",
                R.drawable.chicken,
                "Alamat Restoran 5",
                "https://maps.app.google.gl/Kd1hbopN2DhnY4DQ9"
            )
        )

        menuItems.add(
            MenuItem(
                "Strawberry Milk",
                "Rp 15.000",
                "Minuman segar dikolaborasi dengan susu sapi australia",
                R.drawable.chicken,
                "Alamat Restoran 6",
                "https://maps.app.google.gl/Kd1hbopN2DhnY4DQ9"
            )
        )

        menuItems.add(
            MenuItem(
                "Kentang Goreng",
                "Rp 20.000",
                "Kentang Goreng pakai saus tomat",
                R.drawable.kentang,
                "Alamat Restoran 7",
                "https://maps.app.google.gl/Kd1hbopN2DhnY4DQ9"
            )
        )

        menuItems.add(
            MenuItem(
                "Chicken",
                "Rp 20.000",
                "Chicken dengan sambal ijo",
                R.drawable.chicken,
                "Alamat Restoran 8",
                "https://maps.app.google.gl/Kd1hbopN2DhnY4DQ9"
            )
        )

        menuItems.add(
            MenuItem(
                "Sushi",
                "Rp 35.000",
                "Sushi makanan favorit anak jaman now",
                R.drawable.sushi,
                "Alamat Restoran 9",
                "https://maps.app.google.gl/Kd1hbopN2DhnY4DQ9"
            )
        )

        // Buat adapter untuk RecyclerView
        val adapter = MenuAdapter(menuItems) {
            // Ketika item di RecyclerView diklik, buat Bundle untuk mengirim data ke FragmentDetail
            val args = Bundle()
            args.putString("name", it.name)
            args.putString("price", it.price)
            args.putString("description", it.description)
            args.putInt("imageRes", it.imageRes)
            args.putString("restaurantAddress", it.restaurantAddress)
            args.putString("googleMapsUrl", it.googleMapsUrl)

            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.fragmentDetails, args)
        }

        // Atur layout manager untuk RecyclerView
        binding.rvMenuMakanan.layoutManager = GridLayoutManager(requireContext(), 2)


        binding.rvMenuMakanan.adapter = adapter
    }

    interface FragmentMenuListener {
        fun onMenuItemClicked(args: Bundle)
    }
}
