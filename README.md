
<h1 align="center" style="font-weight: bold;">📊 Bucket-Based Radix Sort Analyzer</h1>

<p align="center">
<a href="#technologies">Technologies</a>
<span>&nbsp; • &nbsp;</span>
<a href="#started">Getting Started</a>
<span>&nbsp; • &nbsp;</span>
<a href="#team">Team Members</a>
</p>

<p align="center">A Java program that implements bucket-based radix sort for sorting numbers and words, with complexity analysis through primitive operation counting and Excel graph plotting.</p>

<h2 id="layout">📸 Output Preview</h2>

<p align="center">
<img src="assets/sample-output-numbers.png" alt="Radix Sort Numbers Output" width="400px">
<img src="assets/sample-output-words.png" alt="Radix Sort Words Output" width="400px">
</p>

<h2 id="technologies">💻 Technologies</h2>

- Java
- Apache POI (optional for Excel export)
- Microsoft Excel (for plotting graphs)
- Git (for version control)

### Suggested IDE
- IntelliJ IDEA
- Eclipse
- Visual Studio Code

<h2 id="started">🚀 Getting Started</h2>

Follow these instructions to run the project locally.

<h3>Prerequisites</h3>

Ensure you have the following installed:

- [Java JDK 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Git](https://git-scm.com/)
- A Java IDE (e.g. IntelliJ)

<h3>Cloning</h3>

Clone the project repository:

```bash
git clone https://github.com/your-username/radix-sort-analyzer.git
```

<h3>Running</h3>

Compile and run the sorting programs from your IDE or terminal:

```bash
# For numeric sorting
javac Sorting.java
java Sorting

# For word sorting
javac WordSorting.java
java WordSorting

# For complexity analysis with counter
javac SortingCounter.java
java SortingCounter

javac WordSortingCounter.java
java WordSortingCounter
```

<h2>📈 Complexity Analysis</h2>

- `SortingCounter.java` and `WordSortingCounter.java` are modified versions that count **primitive operations**.
- Collected data is analyzed and visualized using Microsoft Excel to observe **time complexity trends**.
- Graphs include:
    - Number of operations vs input size for Number Sort
    - Number of operations vs input size for Word Sort

<h2 id="team">🤝 Team Members</h2>

<p>Special thanks to the contributors of this project.</p>
<table>
<tr>

<td align="center">
<a href="https://github.com/your-username">
<img src="https://avatars.githubusercontent.com/u/YOUR_USER_ID?v=4" width="100px;" alt="Profile Picture"/><br>
<sub>
<b>Your Name (Your Student ID)</b>
</sub>
</a>
</td>

</tr>
</table>

<p align="center">
📘 <i>Feel free to fork, contribute, or raise issues to improve this analysis tool.</i>
</p>