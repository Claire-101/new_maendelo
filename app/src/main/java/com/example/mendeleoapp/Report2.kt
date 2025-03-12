package com.example.mendeleoapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Report2 : AppCompatActivity() {

    // Declare variables at the class level so you can use them anywhere in the class
    private lateinit var sharedImageView: ImageView
    private lateinit var placeholderText: TextView
    private lateinit var uploadContainer: FrameLayout

    private val IMAGEPICKCODE = 1000 // Request code for picking image from gallery


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_report2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // SHARING IMAGES FROM GALLERY TO THE APP
        sharedImageView = findViewById(R.id.sharedImageView)
        placeholderText = findViewById(R.id.placeholderText)
        uploadContainer = findViewById(R.id.uploadContainer)

        // Check if this activity was started with an intent to share an image
        if (intent.action == Intent.ACTION_SEND && intent.type?.startsWith("image/") == true) {
            val imageUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)

            if (imageUri != null) {
                sharedImageView.setImageURI(imageUri)
                placeholderText.visibility = View.GONE
            } else {
                Toast.makeText(this, "No image received!", Toast.LENGTH_SHORT).show()
            }
        }


        // ===================== SPINNER CODE ADDED BELOW ===================== //

        // Spinner initialization
        val projectSpinner = findViewById<Spinner>(R.id.projectSpinner)

        // Project list
        val projectList = listOf("Kibera Road Expansion", "Mombasa County Hospital", "Arthi River Irrigation", "Health Clinic")

        // Adapter to populate the spinner
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, projectList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Attach adapter to spinner
        projectSpinner.adapter = spinnerAdapter

        // Optional: Get selected project when user selects one
        projectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedProject = parent.getItemAtPosition(position).toString()
                // Show selected project (optional)
                Toast.makeText(this@Report2, "Selected: $selectedProject", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // ===================== UPLOAD IMAGE ===================== //

        uploadContainer.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGEPICKCODE)
        }
    }

    // Handle result of image picker
    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGEPICKCODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            if (imageUri != null) {
                sharedImageView.setImageURI(imageUri)
                placeholderText.visibility = View.GONE
            }
        }


    }
}