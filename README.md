### Updated `README.md`
```markdown
# Tally Chart Project

This project generates tally chart questions and exports them to an Excel file. The questions include various flower emojis and tally marks.

## Directory Structure

```
tally_chart_project/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── tallychart/
│   │   │   │   │   ├── Main.java
│   │   │   │   │   ├── utils/
│   │   │   │   │   │   ├── ExcelUtils.java
│   │   │   │   │   │   ├── QuestionGenerator.java
│   │   │   │   │   │   ├── Question.java
│   ├── resources/
│   │   ├── report/
│   │   │   ├── tally_chart_options.xlsx
```

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven

## Dependencies

The project uses the following dependencies:

- Apache POI for handling Excel files

These dependencies are managed by Maven.

## Setup

1. **Clone the repository:**

   ```sh
   git clone https://github.com/your-username/tally_chart_project.git
   cd tally_chart_project
   ```

2. **Build the project:**

   ```sh
   mvn clean install
   ```

## Running the Project

### From an IDE

1. **Open the project in your IDE:**

    - If you are using IntelliJ IDEA, select `File -> Open` and choose the `tally_chart_project` directory.
    - If you are using Eclipse, select `File -> Import -> Existing Maven Projects` and choose the `tally_chart_project` directory.

2. **Run the `Main` class:**

    - In your IDE, navigate to `src/main/java/com/tallychart/Main.java`.
    - Right-click on the `Main` class and select `Run 'Main.main()'`.

### From the Command Line

1. **Navigate to the project directory:**

   ```sh
   cd path/to/tally_chart_project
   ```

2. **Run the project:**

   ```sh
   mvn exec:java -Dexec.mainClass="com.tallychart.Main"
   ```

3. **Follow the prompts:**

    - The program will ask you how many questions you want to generate (up to 1000).
    - Finally, it will export the questions to an Excel file located at `src/resources/report/tally_chart_options.xlsx`.

## Notes

- Ensure that no Excel files are open before running the program, as it will attempt to close any open Excel processes.
- The generated Excel file will be saved in the `report` directory.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
```
