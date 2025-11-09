// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract StudentData {
    // Structure for Student
    struct Student {
        uint256 id;
        string name;
        uint256 age;
        string course;
    }

    // Array to store student data
    Student[] public students;

    // Mapping to check if a student exists by ID
    mapping(uint256 => bool) public studentExists;

    // Add new student
    function addStudent(uint256 _id, string memory _name, uint256 _age, string memory _course) public {
        require(!studentExists[_id], "Student with this ID already exists!");
        students.push(Student(_id, _name, _age, _course));
        studentExists[_id] = true;
    }

    // Get student details by ID
    function getStudent(uint256 _id) public view returns (string memory, uint256, string memory) {
        for (uint256 i = 0; i < students.length; i++) {
            if (students[i].id == _id) {
                return (students[i].name, students[i].age, students[i].course);
            }
        }
        revert("Student not found");
    }

    // Get total number of students
    function getTotalStudents() public view returns (uint256) {
        return students.length;
    }

    // Update student course
    function updateCourse(uint256 _id, string memory _newCourse) public {
        for (uint256 i = 0; i < students.length; i++) {
            if (students[i].id == _id) {
                students[i].course = _newCourse;
                return;
            }
        }
        revert("Student not found");
    }

    // Fallback function
    fallback() external payable {
        // Triggered when contract receives Ether or invalid function call
    }

    // Receive Ether function
    receive() external payable {}
}
