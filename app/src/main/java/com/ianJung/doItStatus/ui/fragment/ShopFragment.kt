package com.ianJung.doItStatus.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ianJung.doItStatus.adapter.TodoAdapter
import com.ianJung.doItStatus.databinding.FragmentShopBinding
import com.ianJung.doItStatus.model.Memo
import com.ianJung.doItStatus.singlebungle.Singleton
import com.ianJung.doItStatus.ui.dialog.*
import com.ianJung.doItStatus.viewmodel.MemoViewModel

class ShopFragment : Fragment() {

    private var binding : FragmentShopBinding? = null
    private val memoViewModel: MemoViewModel by viewModels() // 뷰모델 연결
    private val adapter : TodoAdapter by lazy { TodoAdapter(memoViewModel) } // 어댑터 선언



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 뷰바인딩
        binding = FragmentShopBinding.inflate(inflater,container,false)
        binding!!.Gold.text = DoneDialog.gold.toString()
        binding!!.Exp.text=DoneDialog.exp.toString()
        // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
        adapter.setHasStableIds(true)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.swipe.setOnRefreshListener {
            binding!!.Gold.text = DoneDialog.gold.toString()
            binding!!.Exp.text=DoneDialog.exp.toString()
            binding!!.swipe.isRefreshing=false
        }
        binding!!.bubbleTea.setOnClickListener{
            val buyDialog = BuyDialog()
            buyDialog.setCost("버블티",10)
            activity?.let { it1 -> buyDialog.show(it1.supportFragmentManager, "BuyDialog") }
             }

        binding!!.onigiri.setOnClickListener{
            val buyDialog = BuyDialog()
            buyDialog.setCost("오니기리",20)
            activity?.let { it2 -> buyDialog.show(it2.supportFragmentManager, "BuyDialog") }
        }
    }



    // 프래그먼트는 뷰보다 오래 지속 . 프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


}