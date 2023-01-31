package com.ianJung.doItStatus.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ianJung.doItStatus.R
import com.ianJung.doItStatus.adapter.TodoAdapter
import com.ianJung.doItStatus.databinding.FragmentShopBinding
import com.ianJung.doItStatus.databinding.FragmentStatBinding
import com.ianJung.doItStatus.sharedpre.App
import com.ianJung.doItStatus.sharedpre.App.Companion.petexp
import com.ianJung.doItStatus.sharedpre.MysharedPreferences
import com.ianJung.doItStatus.ui.dialog.DoneDialog
import com.ianJung.doItStatus.ui.dialog.GiveDialog
import com.ianJung.doItStatus.ui.dialog.NameDialog
import com.ianJung.doItStatus.viewmodel.MemoViewModel
import java.util.*

class StatFragment : Fragment() {

    lateinit var binding: FragmentStatBinding
    private var level =1
    //private val exp= context?.let { MysharedPreferences(it).getExp().toString() }
    //32번 라인을 실행하고 43번 라인 로그를 확인하면 exp값이 99에요 이말은 32번 라인 실행할땐 context가 null이라서 exp를 읽어오지 못해요.
    private val exp = if(context!=null) MysharedPreferences(requireContext()).getExp().toString() else "99"
    private val memoViewModel: MemoViewModel by viewModels() // 뷰모델 연결
    private val adapter: TodoAdapter by lazy { TodoAdapter(memoViewModel) } // 어댑터 선언
    private val GALLERY = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // 뷰바인딩
        Log.d("태그", "onCreateView: $exp")
        binding = FragmentStatBinding.inflate(inflater, container, false)
        binding!!.goldUser.text = context?.let { MysharedPreferences(it).getGold().toString() }
        //그래서 처음 초기화는 47번 라인으로 진행했고
        binding!!.expUser.text = context?.let { levelLogic(MysharedPreferences(it).getExp().toString()).toString() }
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

        binding.imageView2.setOnClickListener(){
            val giveDialog = GiveDialog()
            activity?.let { it2 -> giveDialog.show(it2.supportFragmentManager, "GiveDialog") }
            Toast.makeText(context, context?.let { MysharedPreferences(it).getPetExp().toString() } , Toast.LENGTH_SHORT).show()
        }

        return binding!!.root
    }


    private fun levelLogic(exp: String): Int {
        var EXP = exp.toInt()
        if(EXP<100)
            level = 1
        if(EXP>=100 && EXP <=1000)
            level = 2
        if(EXP>1000 && EXP <= 2000)
            level = 3
        if (EXP>2000 && EXP <= 3000)
            level = 4

        return level

    }

    private fun petlevelLogic(exp:String):Int{
        var EXP = exp.toInt()
        if(EXP<100)
            level = 1
        if(EXP>=100 && EXP <=1000)
            level = 2
        if(EXP>1000 && EXP <= 2000)
            level = 3
        if (EXP>2000 && EXP <= 3000)
            level = 4

        return when(level){
            1-> return R.drawable.egg
            2-> return R.drawable.lev2cat
            3-> return R.drawable.lev3cat
            else -> {return R.drawable.egg}
        }
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
            binding!!.goldUser.text =  context?.let { MysharedPreferences(it).getGold().toString() }
            //124번 라인도 새로고침 하면 새롭게 exp를 얻어와야되는데 그냥 처음 얻어둔 문자열로 처리하니까 바뀌지 않아요
            //이건 안고친거라 혼자서 고쳐보세요
            binding!!.expUser.text = context?.let { levelLogic(MysharedPreferences(it).getExp().toString()).toString() }
            context?.let { petlevelLogic(MysharedPreferences(it).getPetExp().toString()) }
                ?.let { binding!!.imageView2.setImageResource(it) }

            binding!!.swipe1.isRefreshing=false
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}