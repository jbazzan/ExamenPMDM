package com.unirfp.examenpmdm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.unirfp.examenpmdm.api.ProductoApiService
import com.unirfp.examenpmdm.databinding.ActivityMainBinding
import com.unirfp.examenpmdm.model.Producto
import com.unirfp.examenpmdm.model.ProductoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductoAdapter
    private val productoList = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         initRecyclerView()
        fetchData("products")
    }

    // Función para inicializar el RecyclerView
    private fun initRecyclerView() {
        adapter = ProductoAdapter((productoList))
        binding.rvProductos.layoutManager = LinearLayoutManager(this)
        binding.rvProductos.adapter = adapter

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://peticiones.online/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Función para obtener datos de la API
    private fun fetchData(query : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call : retrofit2.Response<ProductoResponse> = getRetrofit()
                .create(ProductoApiService::class.java)
                .getAllProducts(query);

            val response :ProductoResponse? = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    val productos = response?.resultados ?: emptyList()
                    productoList.clear()
                    productoList.addAll(productos)
                    adapter.notifyDataSetChanged()
            } else {
                runOnUiThread {
                Toast.makeText(this@MainActivity, "Error al obtener los datos", Toast.LENGTH_SHORT).show()
                     }
            }

            }
        }
    }
}