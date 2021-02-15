package com.astaria.lxiv_demo

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsProvider
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.documentfile.provider.DocumentFile
import com.astaria.lxiv.lib.DecoderBuilder
import com.astaria.lxiv.lib.EncoderBuilder
import com.astaria.lxiv.lib.Flag
import com.astaria.lxiv_demo.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadImageButton.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, 100)
        }

        binding.changeStringButton.setOnClickListener {

//            val projectDir = File(getExternalFilesDir(null), "LXIV")
//            val projectImagesDir = File(projectDir.absolutePath, "Images")
//            if (!projectDir.exists()) projectDir.mkdir()
//            if (!projectImagesDir.exists()) projectImagesDir.mkdir()
//
//            println(projectImagesDir.absolutePath)
//
//            val file = File(projectImagesDir.absolutePath, "KaGracia.jpg")
//            val bitmap = DecoderBuilder().setBase64String(binding.tietInput.text.toString()).setEncoderFlag(EncoderFlag.DEFAULT).build()
//            val outputStream = contentResolver.openOutputStream(file.toUri())
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//            outputStream?.close()


            println(getExternalFilesDir(null))
            println(File.separator)
            println(Environment.getDataDirectory().absolutePath)
            println(Environment.DIRECTORY_PICTURES)
            println(Environment.DIRECTORY_PICTURES)

            // Create File
            val contentValues = ContentValues()
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
//            val xx = contentResolver.openOutputStream(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                // Null Folder
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + "LXIV")
                contentValues.put(MediaStore.Images.Media.IS_PENDING, true)
                contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                val imageContentValues = ContentValues()
                imageContentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + "LXIV")
                imageContentValues.put(MediaStore.Images.ImageColumns.TITLE, "${Calendar.getInstance().time}")
                imageContentValues.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, "${Calendar.getInstance().time}")
                imageContentValues.put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/jpeg")
                imageContentValues.put(MediaStore.Images.Media.IS_PENDING, true)

                val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageContentValues) ?: throw NullPointerException()

//                val file = File(dirUri?.path, "${Calendar.getInstance().time}.jpg")

                val bitmap = DecoderBuilder().setBase64String(binding.tietInput.text.toString()).setFlag(Flag.DEFAULT).buildAsBitmap()
                val outputStream = contentResolver.openOutputStream(imageUri)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream?.close()
                imageContentValues.put(MediaStore.Images.Media.IS_PENDING, false)
                contentResolver.update(imageUri, imageContentValues, null, null)

//                val inps = contentResolver.openInputStream(imageUri)
//                inps

//                val xxx = File(inps)
//                println(xxx.absolutePath)
//                println(imageUri.path)
//                MediaScannerConnection.scanFile(this, arrayOf(), arrayOf("*/*")
//                ) { s: String, uri: Uri ->
//                    println(s)
//                    println(uri)
//                }

//                MediaScannerConnection.scanFile(this, arrayOf(dirUri?.path), arrayOf("*/*"), null)
//                val file =
//                if (file.exists()) {
//                    println("EXIST")
//                    file.delete()
//                }
            } else {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        println("LANGSUNG GRANTED")
                        val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + "LXIV")
                        println(directory.absolutePath)
                        if (!directory.exists()) print(directory.mkdirs())

                        val file = File(directory.absolutePath, "${Calendar.getInstance().time}.jpg")

                        val bitmap = DecoderBuilder().setBase64String(binding.tietInput.text.toString()).setFlag(Flag.DEFAULT).buildAsBitmap()
                        val outputStream = contentResolver.openOutputStream(file.toUri())
//                        val os = FileOutputStream(file)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        outputStream?.close()
                        // Update file
                        MediaScannerConnection.scanFile(this, arrayOf(file.absolutePath), arrayOf("*/*"), null)
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//                        outputStream?.close()

                    } else {
                        requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 10)
                    }
                }
//                println("ELSE")
//                val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + "ELSE_DUAR_3")
//                println(directory.absolutePath)
//                if (!directory.exists()) {
//                    print(directory.mkdirs())
//                }

//                contentValues.put(MediaStore.MediaColumns., Environment.DIRECTORY_PICTURES + File.separator + "IF_DUAR_3")
////                contentValues.put(MediaStore.Images.Media.IS_PENDING, true)
//                val xxuri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//                val imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
////                val imageDirxxx = File(Environment.getExternalStorageDirectory().absolutePath + File.separator + "ELSE_DUAR_3")
//                val imageDirxxx = File(getDir(Environment.DIRECTORY_PICTURES), "ELSE_DUAR_3")
////                println(imageDir.absolutePath)
////                val imageDir2 = File(imageDir.absolutePath, "ELSE_DUAR")
//                if (!imageDirxxx.exists()) {
//                    println("NOT EXIST")
//                    println(imageDirxxx.absolutePath)
//                    println(imageDirxxx.mkdir())
////                    if (imageDir2.exists()) println("CREATED")
//                }
            }
//                val xxx = MediaStore.Images.Media.getContentUri(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                println(contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues).toString())
//                val path = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//                val projectDir = File(path)
//                val projectImagesDir = File(projectDir.absolutePath, "Images")
//                println(projectImagesDir.absolutePath)
//                if (!projectDir.exists()) projectDir.mkdirs()
//                if (!projectImagesDir.exists()) projectImagesDir.mkdirs()
//            }


//            val directoryPickerIntent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
//                addCategory(Intent.CATEGORY_OPENABLE)
//                type = "image/jpeg"
//            }
//            startActivityForResult(directoryPickerIntent, 101)

//            startActivityForResult(directoryPickerIntent,
////                Intent.createChooser(directoryPickerIntent, "Choose directory"),
////                Intent.createChooser(directoryPickerIntent, "Choose directory"),
//                101
//            )

//            binding.imageView.setImageBitmap(DecoderBuilder(binding.tietInput.text.toString(), EncoderFlags.DEFAULT).build())

//            MaterialDialog(this).show {
//                fileChooser(applicationContext) { dialog , file ->
//                    dialog.cancelable(true)
//                    println(file.absolutePath)
//                }
//            }

//            val documentPickerIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
//                type = "file/*"
//            }
//            startActivityForResult(documentPickerIntent, 101)
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
////                intent.type = "file?*"
////                startActivityForResult(intent, 101)
//            } else {
//
//                println("OS UNDER 21")
//            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            10 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    println("GRANTED")
                    val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + "LXIV")
                    println(directory.absolutePath)
                    if (!directory.exists()) print(directory.mkdirs())

                    val file = File(directory.absolutePath, "KaGracia2.jpg")
                    val bitmap = DecoderBuilder().setBase64String(binding.tietInput.text.toString()).setFlag(Flag.DEFAULT).buildAsBitmap()
//                    val outputStream = contentResolver.openOutputStream(file.toUri())
                    val os = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
                    os.close()
                } else {
                    println("The app was not allowed to write in your storage")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            100 -> {
                if (resultCode == RESULT_OK) {
                    try {
                        if (data == null) throw NullPointerException()
                        val uri = data.data

                        binding.hasilConvert.setText(EncoderBuilder(this).setUri(uri).setQuality(100).setCompressFormat(Bitmap.CompressFormat.JPEG).setFlag(Flag.DEFAULT).build())
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
            }
            101 -> {
                println(resultCode)
                if (resultCode == RESULT_OK) {
                    try {
                        // ACTION_CREATE_DOC
                        if (data == null) throw NullPointerException()
                        val fpath = data.data ?: throw NullPointerException()
                        println(fpath)
                        val bitmap = DecoderBuilder().setBase64String(binding.tietInput.text.toString()).setFlag(Flag.DEFAULT).buildAsBitmap()

                        val outputStream = contentResolver.openOutputStream(fpath)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        outputStream?.close()

//                        println(fpath.)
//                        val fos = FileOutputStream(xxx)
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
//                        fos.flush()
//                        fos.close()
//                        if (data == null) throw NullPointerException()
//                        val fpath = data.data ?: throw NullPointerException()
//                        println("FPATH : ${fpath.path}")
//                        println("FPATH : ${data.dataString}")
//
//                        val bitmap = DecoderBuilder(
//                            binding.tietInput.text.toString(),
//                            EncoderFlags.DEFAULT
//                        ).build()
//
//                        binding.imageView.setImageBitmap(bitmap)
//
//                        println("ENV : ${externalCacheDir?.absolutePath}")
//
////                        val x = contentResolver.openOutputStream(fpath)
//
//                        println("File Util")
//                        println(DocumentFile.fromTreeUri(this, fpath)?.listFiles()?.size)
////                        DocumentFile.fromTreeUri(this, fpath)?.createFile("image/jpeg", "gege")
////                        val xxx = File(DocumentFile.fromTreeUri(this, fpath)?.uri + "/" + "gege.jpeg")
//                        val inputStream = DocumentFile.fromTreeUri(this, fpath)?.uri?.let {
//                            contentResolver.openInputStream(
//                                it
//                            )
//                        }
//                        val file = File(inputStream., "gege.jpeg").absoluteFile
//                        println(file)
//                        println(FileUtil().getFullPathFromTreeUri(fpath, this))
//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                            println(DocumentsContract.buildDocumentUriUsingTree(fpath, DocumentsContract.Document.MIME_TYPE_DIR))
//                        }
//
////                        val filex = File(fpath.path + File.separator + "gege.jpeg")
//
////                        val fos = FileOutputStream(xxx)
////                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
////                        fos.flush()
////                        fos.close()
//
////                        val uri = data.data ?: throw NullPointerException()
////                        println(uri)
////                        val docUri = DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri))
////                        println("===============================================")
////                        println(docUri)
////                        println(ASFUriHelper().getPath(this, docUri))
////                        println("===============================================")
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

            }
        }
    }

//    fun getRealPathFromURI(context: Context, contentUri: Uri?): String? {
//        var cursor: Cursor? = null
//        return try {
//            val proj = arrayOf(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            cursor = context.getContentResolver().query(contentUri, proj, null, null, null)
//            val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//            cursor.moveToFirst()
//            cursor.getString(column_index)
//        } finally {
//            if (cursor != null) {
//                cursor.close()
//            }
//        }
//    }

}