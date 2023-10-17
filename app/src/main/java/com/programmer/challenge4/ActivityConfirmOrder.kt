
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.programmer.challenge4.MainActivity
import com.programmer.challenge4.adapter.ConfirmOrderAdapter
import com.programmer.challenge4.databinding.FragmentConfirmOrderBinding
import com.programmer.challenge4.viewmodel.CartViewModel
import com.programmer.challenge4.viewmodel.ViewModelFactory

class ConfirmOrderActivity : AppCompatActivity() {

    private lateinit var binding: FragmentConfirmOrderBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var confirmOrderAdapter: ConfirmOrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentConfirmOrderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this, ViewModelFactory(application)).get(
            CartViewModel::class.java)

        confirmOrderAdapter = ConfirmOrderAdapter()

        setupRecyclerView()
        observeCartItems()

        binding.btnBack2.setOnClickListener{
            finish()
        }

        binding.btnPlaceOrder.setOnClickListener {
            val deliveryMethod = if (binding.btnTakeAway.isChecked) {
                binding.btnTakeAway.text.toString()
            } else if (binding.btnDelivery.isChecked){
                binding.btnDelivery.text.toString()
            } else {
                ""
            }

            val paymentMethod = if (binding.btnCash.isChecked) {
                binding.btnCash.text.toString()
            } else if (binding.btnWallet.isChecked){
                binding.btnWallet.text.toString()
            }else{
                ""
            }

            val totalPrice = confirmOrderAdapter.calculateTotalPrice()
            val orderSummary = "Pesanan seharga: Rp. $totalPrice\n" +
                    "Metode Pengiriman: $deliveryMethod\n" +
                    "Metode Pembayaran: $paymentMethod"

            if (deliveryMethod.isEmpty() || paymentMethod.isEmpty()){
                Toast.makeText(this@ConfirmOrderActivity, "Tolong isi metode yang masih kosong!", Toast.LENGTH_SHORT).show()
            }else{
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("Order Summary")
                    .setMessage(orderSummary)
                    .setPositiveButton("Berhasil") { _, _ ->
                        // Delete all items from the database
                        viewModel.deleteAllCartItems()
                        // Navigate back to the home page
                        startActivity(Intent(this, MainActivity::class.java))
                        finishAffinity() // Finish activity ini agar tidak bisa kembali dengan back button
                    }
                    .create()
                alertDialog.show()
            }



        }



    }
    private fun setupRecyclerView() {

        binding.root.apply {
            confirmOrderAdapter
            LinearLayoutManager(context)
        }
    }
    private fun observeCartItems() {
        viewModel.allCartItems.observe(this, Observer { cartItems ->
            confirmOrderAdapter.submitList(cartItems)
            val totalPrice = confirmOrderAdapter.calculateTotalPrice()
            binding.txtTotalPayment.text = "Total Price: Rp. $totalPrice"
        })

    }
}