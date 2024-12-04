package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

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

        // Hiển thị thông tin sinh viên trong các trường nhập liệu
        currentStudent?.let { student ->
            edtFullName.setText(student.fullName)
            edtStudentCode.setText(student.studentCode)
        }

        // Cập nhật thông tin sinh viên khi bấm "Cập nhật"
        btnSave.setOnClickListener {
            currentStudent?.let { student ->
                student.fullName = edtFullName.text.toString()
                student.studentCode = edtStudentCode.text.toString()

                // Cập nhật thông tin sinh viên trong StudentRepository
                studentRepository.updateStudent(student)

                // Quay lại StudentListFragment
                findNavController().navigate(R.id.action_editStudentFragment_to_studentListFragment)
            }
        }

        return view
    }
}
