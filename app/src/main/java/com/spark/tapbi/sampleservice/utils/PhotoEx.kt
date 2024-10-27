package com.spark.tapbi.sampleservice.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.net.Uri
import android.util.Base64
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.content.FileProvider
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

fun bitmapToBase64(bm: Bitmap): String {
    val baos = ByteArrayOutputStream()
    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

fun base64ToBitmap(base64String: String): Bitmap {
    val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
}

fun bitmapToUri(context: Context, inImage: Bitmap): Uri? {
    val tempFile = File(context.cacheDir, "temp_${System.currentTimeMillis()}.png")

    return try {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes)
        val bitmapData = bytes.toByteArray()

        FileOutputStream(tempFile).use { fileOutputStream ->
            fileOutputStream.write(bitmapData)
            fileOutputStream.flush()
        }
        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", tempFile)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun filePathToBitmap(filePath: String): Bitmap? {
    return BitmapFactory.decodeFile(filePath)
}

fun mergeImages(
    backgroundImageView: ImageFilterView,
    foregroundImageView: ImageFilterView
): Bitmap {
    val width = backgroundImageView.width
    val height = backgroundImageView.height

    val resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(resultBitmap)

    val backgroundBitmap = Bitmap.createBitmap(
        backgroundImageView.width,
        backgroundImageView.height,
        Bitmap.Config.ARGB_8888
    )
    val backgroundCanvas = Canvas(backgroundBitmap)
    backgroundImageView.draw(backgroundCanvas)

    val backgroundPaint = Paint()
    backgroundPaint.alpha =
        (backgroundImageView.alpha * 255).toInt()

    canvas.drawBitmap(backgroundBitmap, 0f, 0f, backgroundPaint)

    val foregroundBitmap = Bitmap.createBitmap(
        foregroundImageView.width,
        foregroundImageView.height,
        Bitmap.Config.ARGB_8888
    )
    val foregroundCanvas = Canvas(foregroundBitmap)
    foregroundImageView.draw(foregroundCanvas)

    val foregroundPaint = Paint()
    foregroundPaint.alpha =
        (foregroundImageView.alpha * 255).toInt()

    canvas.drawBitmap(
        foregroundBitmap,
        0f,
        (height - foregroundImageView.height) / 2f,
        foregroundPaint
    )

    return resultBitmap
}

