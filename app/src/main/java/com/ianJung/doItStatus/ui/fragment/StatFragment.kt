package com.ianJung.doItStatus.ui.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ianJung.doItStatus.adapter.TodoAdapter
import com.ianJung.doItStatus.databinding.FragmentStatBinding
import com.ianJung.doItStatus.viewmodel.MemoViewModel

class StatFragment : Fragment() {

    lateinit var binding: FragmentStatBinding
    private val memoViewModel: MemoViewModel by viewModels() // 뷰모델 연결
    private val adapter: TodoAdapter by lazy { TodoAdapter(memoViewModel) } // 어댑터 선언
    private val GALLERY = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // 뷰바인딩
        binding = FragmentStatBinding.inflate(inflater, container, false)
        // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
        adapter.setHasStableIds(true)

        binding.profileImgUser.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, GALLERY)
        }
        return binding!!.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {

        }
        when (requestCode) {
            GALLERY -> {
                val uri: Uri? = data?.data

                if (uri != null) {
                    Glide.with(this)
                        .load(uri)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.profileImgUser)
                }
            }
        }
    }
}