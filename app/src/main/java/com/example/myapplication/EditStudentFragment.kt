package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class EditStudentFragment : Fragment() {
    private lateinit var studentRepository: StudentRepository
    private var currentStudent: Student? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        val edtFullName: EditText = view.findViewById(R.id.edtFullName)
        val edtStudentCode: EditText = view.findViewById(R.id.edtStudentCode)
        val btnSave: Button = view.findViewById(R.id.btnSave)

        studentRepository = StudentRepository()

        // Lấy thông tin sinh viên từ arguments
        val studentId = arguments?.getString("studentId")
        currentStudent = studentId?.let { studentRepository.getStudentById(it) }

        currentStudent?.let { student ->
            edtFullName.setText(student.fullName)
            edtStudentCode.setText(student.studentCode)
        }

        btnSave.setOnClickListener {
            currentStudent?.let { student ->
                student.fullName = edtFullName.text.toString()
                student.studentCode = edtStudentCode.text.toString()
                studentRepository.updateStudent(student)
                // Quay lại màn hình danh sách
            }
        }

        return view
    }
}
