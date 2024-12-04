package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.util.UUID

class AddStudentFragment : Fragment() {
    private lateinit var studentRepository: StudentRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        val edtFullName: EditText = view.findViewById(R.id.edtFullName)
        val edtStudentCode: EditText = view.findViewById(R.id.edtStudentCode)
        val btnSave: Button = view.findViewById(R.id.btnSave)

        studentRepository = StudentRepository()

        btnSave.setOnClickListener {
            val fullName = edtFullName.text.toString()
            val studentCode = edtStudentCode.text.toString()

            if (fullName.isNotEmpty() && studentCode.isNotEmpty()) {
                val newStudent = Student(
                    UUID.randomUUID().toString(),
                    fullName,
                    studentCode
                )
                studentRepository.addStudent(newStudent)
                // Quay lại màn hình danh sách
            }
        }

        return view
    }
}