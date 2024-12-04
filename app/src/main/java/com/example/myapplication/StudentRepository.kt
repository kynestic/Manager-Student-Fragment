package com.example.myapplication

class StudentRepository {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            students[index] = student
        }
    }

    fun removeStudent(student: Student) {
        students.removeIf { it.id == student.id }
    }

    fun getAllStudents(): List<Student> = students.toList()

    fun getStudentById(id: String): Student? = students.find { it.id == id }

}