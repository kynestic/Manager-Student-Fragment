package com.example.myapplication

class StudentRepository {
    private val students = mutableListOf<Student>()

    init {
        // Thêm sinh viên mẫu vào danh sách
        students.add(Student("1", "Nguyễn Văn A", "SV001"))
        students.add(Student("2", "Trần Thị B", "SV002"))
        students.add(Student("3", "Lê Văn C", "SV003"))
        students.add(Student("4", "Phạm Thị D", "SV004"))
    }

    // Thêm sinh viên vào danh sách
    fun addStudent(student: Student) {
        students.add(student)
    }

    // Cập nhật thông tin sinh viên
    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            students[index] = student
        }
    }

    // Xóa sinh viên khỏi danh sách
    fun removeStudent(student: Student) {
        students.removeIf { it.id == student.id }
    }

    // Lấy danh sách tất cả sinh viên
    fun getAllStudents(): List<Student> = students.toList()

    // Lấy thông tin sinh viên theo ID
    fun getStudentById(id: String): Student? = students.find { it.id == id }
}
