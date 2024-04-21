package com.example.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.FragmentOneBinding
import com.example.jetpack.databinding.ItemRecyclerviewBinding

class MyViewHolder(val bnd: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(bnd.root)
        // ㄴ RecyclerView.ViewHolder -> 추상클래스. 추상클래스는 직접 객채로 생성하는 건 불가능하기 때문에 이렇게 미리 정의해두고 써야함

class MyAdapter(val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =

        MyViewHolder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


//    override fun getItemCount(): Int {
//        return datas.size
//    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).bnd
        binding.itemData.text = datas[position]

    }


}

class OneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(inflater, container, false)
        val datas = mutableListOf<String>()
        for ( i in 1..9)
            datas.add("item $i")

        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = MyAdapter(datas)
        return binding.root


//        return super.onCreateView(inflater, container, savedInstanceState)
    }

}