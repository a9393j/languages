# Master DSA Interview Preparation Guide Generator

A comprehensive Python tool that generates a polished PDF guide for Data Structures and Algorithms interview preparation, specifically tailored for Java developers.

## 🎯 Features

- **50+ Essential Interview Problems** with detailed solutions
- **Multiple Solution Approaches** for each problem (iterative, recursive, optimized)
- **Complete Java Implementations** with inline comments
- **Time & Space Complexity Analysis** for all solutions
- **Visual Diagrams** using matplotlib for algorithm visualization
- **Common Patterns & Templates** section
- **System Design Quick Notes** for technical interviews
- **Practice Tracking Tables** for progress monitoring
- **Hyperlinked Table of Contents** with LeetCode links
- **Topic-wise PDF Generation** for focused study

## 📋 Requirements

- Python 3.7 or higher
- Required Python packages (see requirements.txt)

## 🚀 Quick Start

### 1. Install Dependencies

```bash
pip install -r requirements.txt
```

### 2. Generate the PDF Guide

```bash
cd java/dsa
python3 build_master_dsa_pdf.py
```

### 3. Output Files

The script will generate:

- **`java/dsa/Master_DSA_Interview_Preparation_Guide_Java_Pro_Edition.pdf`** - Complete guide (main output)
- **`java/dsa/output/parts/`** directory with topic-specific PDFs:
  - `Arrays Strings.pdf`
  - `Linked Lists.pdf`
  - `Trees.pdf`
  - `Graphs.pdf`
  - `Heaps.pdf`
  - `Stacks Queues.pdf`
  - `Dynamic Programming.pdf`

## 📚 What's Included

### Problem Categories

1. **Arrays & Strings**
   - Two Sum, Three Sum, Product of Array Except Self
   - Longest Substring Without Repeating Characters
   - Maximum Subarray (Kadane's Algorithm)

2. **Linked Lists**
   - Reverse Linked List, Merge Two Sorted Lists
   - Linked List Cycle Detection

3. **Trees**
   - Binary Tree Level Order Traversal
   - Lowest Common Ancestor

4. **Graphs**
   - Number of Islands, Course Schedule
   - DFS/BFS implementations

5. **Heaps**
   - Kth Largest Element
   - Priority Queue operations

6. **Stacks & Queues**
   - Valid Parentheses, Daily Temperatures
   - Monotonic Stack patterns

7. **Dynamic Programming**
   - Coin Change, Longest Increasing Subsequence
   - Bottom-up and Top-down approaches

### Each Problem Includes

- **Problem Statement** with clear description
- **Multiple Approaches** (brute force, optimized, alternative methods)
- **Complete Java Solutions** with detailed comments
- **Complexity Analysis** (time and space)
- **Visual Diagrams** when helpful
- **Interviewer Notes** with edge cases and follow-ups
- **LeetCode Links** for practice

### Additional Sections

- **Common Patterns & Templates**
  - Two Pointers, Sliding Window, Fast/Slow Pointers
  - Binary Search, BFS/DFS templates
  - Dynamic Programming patterns

- **System Design Quick Notes**
  - Load Balancers, Caching strategies
  - Database Sharding, Message Queues
  - CAP Theorem, Consistency Models

- **Complexity Cheat Sheet**
  - Data structure operations complexity
  - Algorithm time/space complexity reference

## 🔧 Customization

### Adding New Problems

Edit the `_load_problem_database()` method in `build_master_dsa_pdf.py` to add new problems:

```python
{
    "name": "Your Problem Name",
    "leetcode_num": 123,
    "difficulty": "Medium",
    "tags": ["Array", "Two Pointers"],
    "description": "Problem description",
    "approaches": ["Approach 1", "Approach 2"],
    "solutions": {
        "solution1": "Java code here",
        "solution2": "Alternative Java code"
    },
    "complexity": {
        "solution1": "Time: O(n), Space: O(1)",
        "solution2": "Time: O(n²), Space: O(n)"
    },
    "notes": "Important notes for interviewers"
}
```

### Modifying Styling

Update the `COLORS` dictionary and `_setup_custom_styles()` method to change:
- Color scheme
- Font sizes
- Spacing
- Layout preferences

### Adding Diagrams

Extend the `_create_diagram()` method to add new diagram types for specific algorithms.

## 📖 Usage Examples

### Basic Generation
```bash
cd java/dsa
python3 build_master_dsa_pdf.py
```

### Reading Existing PDFs
The script automatically detects and extracts problems from:
- `java/dsa/Algo expert.pdf`
- `java/dsa/2_week_interview_prep_plan.pdf`

If these files exist, their problem titles will be included in the master problem list.

## 🎨 PDF Features

- **Professional Layout** with consistent styling
- **Color-coded Sections** for easy navigation
- **Clickable LeetCode Links** in table of contents
- **Syntax-highlighted Code** blocks
- **Visual Diagrams** for complex algorithms
- **Print-friendly Format** (A4 size)
- **Bookmark Navigation** for easy PDF navigation

## 🐛 Troubleshooting

### Common Issues

1. **Missing Dependencies**
   ```bash
   pip install --upgrade pip
   pip install -r requirements.txt
   ```

2. **PDF Reading Errors**
   - Ensure PyPDF2 or pdfplumber is installed
   - Check if PDF files are corrupted

3. **Diagram Generation Issues**
   - Ensure matplotlib is properly installed
   - Check if output directory is writable

4. **Memory Issues with Large PDFs**
   - The script processes problems in batches
   - Consider reducing the number of problems if memory is limited

### Error Messages

- `PDF_READER_AVAILABLE = False`: Install PyPDF2 or pdfplumber
- `Could not create diagram`: Check matplotlib installation
- `Permission denied`: Ensure write permissions for output directory

## 📊 Performance

- **Generation Time**: ~2-5 minutes for full guide
- **Output Size**: ~5-15 MB depending on content
- **Memory Usage**: ~100-200 MB during generation
- **Compatibility**: Works on Windows, macOS, Linux

## 🤝 Contributing

To contribute to this project:

1. Fork the repository
2. Add new problems to the database
3. Improve diagram generation
4. Enhance styling and layout
5. Submit a pull request

## 📄 License

This project is open source and available under the MIT License.

## 🙏 Acknowledgments

- LeetCode for problem definitions and examples
- ReportLab for PDF generation capabilities
- Matplotlib for diagram creation
- The Java community for best practices and patterns

---

**Happy Coding and Good Luck with Your Interviews! 🚀**