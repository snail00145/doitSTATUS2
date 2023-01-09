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
import com.ianJung.doItStatus.ui.dialog.DoneDialog
import com.ianJung.doItStatus.ui.dialog.NameDialog
import com.ianJung.doItStatus.viewmodel.MemoViewModel
import java.util.*

class StatFragment : Fragment() {

    lateinit var binding: FragmentStatBinding
    private var level =1
    private val exp= DoneDialog.exp.toString()
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
        binding!!.goldUser.text = DoneDialog.gold.toString()
        binding!!.expUser.text = levelLogic(exp).toString()

        // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
        adapter.setHasStableIds(true)
        
        //사진 온클릭
        binding.profileImgUser.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, GALLERY)
        }
        //이름 온클릭
        binding.nameUserBtn.setOnClickListener {
            nameEdit()
        }

        return binding!!.root
    }

    private fun levelLogic(exp: String): Int {
        var EXP = exp.toInt()
        if(EXP<100)
            level = 1
        else if(EXP>=100 && EXP <=1000)
            level = 2
        else if(EXP>1000 && EXP <= 2000)
            level = 3
        else if (EXP>2000 && EXP <= 3000)
            level = 4
        return level


    }

    //사진 업로드
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

    //이름 수정 다이얼 로그
    private fun nameEdit() {
        val nameDialog = NameDialog(requireActivity(), this)
        nameDialog.show()
    }

    //수정할 이름으로 값 변경
    fun onOkButtonClicked(content: String) {
        binding.nameUserBtn.text = content
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.swipe1.setOnRefreshListener {
            binding!!.goldUser.text = DoneDialog.gold.toString()
            binding!!.expUser.text = levelLogic(exp).toString()
            binding!!.swipe1.isRefreshing=false
        }
    }
}
