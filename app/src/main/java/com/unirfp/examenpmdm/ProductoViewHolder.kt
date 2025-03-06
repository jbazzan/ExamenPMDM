package com.unirfp.examenpmdm


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unirfp.examenpmdm.databinding.ItemproductoBinding
import com.unirfp.examenpmdm.model.Producto

class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemproductoBinding.bind(itemView)

    fun bind(producto: Producto) {
        Picasso.get().load(producto.imagen).into(binding.ivImagen)
        binding.tvNombre.setText("Nombre: " + producto.nombre)
        binding.tvPrecio.setText("Precio: " + producto.precio)

    }

}