package com.ianJung.doItStatus.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.ianJung.doItStatus.R

class BuyDialog(context : Context, buyDialogInterface: BuyDialogInterface) : Dialog(context) {

    // 액티비티에서 인터페이스를 받아옴
    private var buyDialogInterface: BuyDialogInterface = buyDialogInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_buydialog)

        var buyButton : Button = findViewById(R.id.buyButton)
        var cancelButton : Button = findViewById(R.id.notBuyButton)

        // 배경 투명하게 바꿔줌
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        buyButton.setOnClickListener {
        dismiss()
        }

        // 취소 버튼 클릭 시 종료
        cancelButton.setOnClickListener { dismiss()}
    }
}