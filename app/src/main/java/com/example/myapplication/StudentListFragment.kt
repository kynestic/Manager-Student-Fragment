package com.example.myapplication

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentListFragment : Fragment() {
    private lateinit var studentRepository: StudentRepository
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewStudents)
        studentRepository = StudentRepository()
        studentAdapter = StudentAdapter(studentRepository.getAllStudents()) { student ->
            // Xử lý khi click vào một sinh viên
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = studentAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerForContextMenu(view.findViewById(R.id.recyclerViewStudents))
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu_student, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        val student = studentRepository.getAllStudents()[position]

        return when (item.itemId) {
            R.id.action_edit -> {
                // Điều hướng đến EditStudentFragment
                true
            }
            R.id.action_remove -> {
                studentRepository.removeStudent(student)
                studentAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                // Điều hướng đến AddStudentFragment
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}