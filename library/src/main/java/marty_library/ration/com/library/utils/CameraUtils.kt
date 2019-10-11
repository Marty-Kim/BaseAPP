package marty_library.ration.com.library.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.MediaScannerConnection
import android.os.Environment
import android.provider.MediaStore
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import android.media.ExifInterface
import android.net.Uri
import marty_library.ration.com.library.utils.PROPERTY.INTENT_REQ_PICK_FROM_ALBUM


class CameraUtils {
    companion object {
// todo Camera Capture Image Add
        // onActivityResult Callback Init
        var APP_NAME : String? = null
        /**
         *  갤러리 호출
         */
        fun goToAlbum(activity: Activity) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
//            intent.setPackage("");
            if (intent.resolveActivity(activity.packageManager) != null) {
                activity.startActivityForResult(intent, INTENT_REQ_PICK_FROM_ALBUM)
            }
        }

        /**
         *  이미지 저장
         */
        fun saveImage(context: Context, bitmap: Bitmap): String {
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
            val timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
            val imageFileName = APP_NAME + "_$timeStamp.jpg"
            val storageDirName = Environment.getExternalStorageDirectory().toString() +  "/$APP_NAME/"

            val storageDir = File(storageDirName)
            if (! storageDir.exists()) {
                storageDir.mkdirs()
            }

            try {
                val file = File(storageDirName, imageFileName)
                file.createNewFile()

                val fo = FileOutputStream(file)
                fo.write(bytes.toByteArray())
                MediaScannerConnection.scanFile(context,
                        arrayOf(file.path),
                        arrayOf("image/jpeg"), null)
                fo.close()
                BDEBUG.debug("path = " + file.absolutePath)
                return file.absolutePath
            } catch (e: Exception) {
                e.printStackTrace()
                return ""
            }
        }


        /**
         *  실제 이미지 경로 반환
         */
        fun getImagePath(context: Context, uri: Uri?): String{
//            val cursor = MediaStore.Images.Media.query(context.contentResolver, uri, null)
//            cursor.moveToFirst()
//            return cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            return RealPathUtil.getRealPath(context,uri)
        }

        /**
         * EXIF 정보를 회전각도로 변환하는 메서드
         */
        private fun exifOrientationToDegrees(exifOrientation: Int): Int {
            return when (exifOrientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> 90
                ExifInterface.ORIENTATION_ROTATE_180 -> 180
                ExifInterface.ORIENTATION_ROTATE_270 -> 270
                else -> 0
            }
        }

        /**
         *  EXIF 정보를 통해 Bitmap 을 회전
         */
        fun rotate(bitmap: Bitmap?, path: String?): Bitmap? {
            var rotateBitmap = bitmap
            val exif = ExifInterface(path)
            val exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            val degrees = exifOrientationToDegrees(exifOrientation)
            if (degrees != 0 && rotateBitmap != null) {
                val matrix = Matrix()
                matrix.setRotate(degrees.toFloat(), rotateBitmap.width.toFloat() / 2,
                        rotateBitmap.height.toFloat() / 2)

                try {
                    val converted = Bitmap.createBitmap(rotateBitmap, 0, 0,
                            rotateBitmap.width, rotateBitmap.height, matrix, true)
                    if (rotateBitmap != converted) {
                        rotateBitmap.recycle()
                        rotateBitmap = converted
                    }
                } catch (ex: OutOfMemoryError) {
                    // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
                }

            }
            return rotateBitmap
        }

        fun collaboDirectoryRemove() {
            val storageDirName = Environment.getExternalStorageDirectory().toString() +  "/$APP_NAME/"

            val storageDir = File(storageDirName)
            if (storageDir.exists()) {
                storageDir.delete()
            }
        }

    }
}