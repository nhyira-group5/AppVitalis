package com.example.vitalisapp.DTO.Pagamento

import android.graphics.Bitmap
import java.time.LocalDateTime

class PaymentKoinDto(
    var paymentComplete: Boolean? = null,
    var dateExpirePayment: LocalDateTime? = null,
    var paymentId: Long? = null,
    var qrCodeBitmap: Bitmap? = null
)