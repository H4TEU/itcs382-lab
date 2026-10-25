# Lab Report, Code Quality and Design Quality

Repository URL: https://github.com/H4TEU/itcs382-lab

## Tools

| Item | Version or name |
| --- | --- |
| JDK | Eclipse Temurin OpenJDK 25.0.5 (LTS) |
| Maven | Apache Maven 3.10.0 |
| IDE | VS Code (1.143.1) |
| SonarQube for IDE | SonarQube for IDE by SonarSource 4.29.0 |
| AI tool and exact model | GitHub Copilot - Auto(mai-code-1.1-flash) |

## Task 1. SonarQube on the starter code

Commit of the starter, before any change: 28b25ec

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | java:S106 | Main.java:9 | Replace this use of System.out by a logger. |
| 2 | java:S106 | Main.java:15 | Replace this use of System.out by a logger. |
| 3 | java:S106 | Main.java:17 | Replace this use of System.out by a logger. |
| 4 | java:S106 | Main.java:22 | Replace this use of System.out by a logger. |
| 5 | java:S1128 | ParcelService.java:6 | Remove this unused import 'java.util.Date'. |
| 6 | java:S1068 | ParcelService.java:12 | Remove this unused "maxParcels" private field. |
| 7 | java:S106 | ParcelService.java:30 | Replace this use of System.out by a logger. |
| 8 | java:S4973 | ParcelService.java:42 | Strings and Boxed types should be compared using "equals()". |
| 9 | java:S106 | ParcelService.java:67 | Replace this use of System.out by a logger. |
| 10 | java:S106 | ParcelService.java:69 | Replace this use of System.out by a logger. |
| 11 | java:S2095 | ParcelService.java:76 | Use try-with-resources or close this "FileWriter" in a "finally" clause. |
| 12 | java:S108 | ParcelService.java:80 | Remove this block of code, fill it in, or add a comment explaining why it is empty. |
| 13 | java:S1104 | Parcel.java:7 | Make status a static final constant or non-public and provide accessors if needed. |
## Task 2. AI review of the starter code

| Item | Value |
| --- | --- |
| Date | 2026-10-25 |
| AI tool and exact model | GitHub Copilot - Auto(mai-code-1.1-flash) |
| Commit reviewed | 28b25ec |
| How the AI read the files (attached the files, opened the folder, or pasted the files) | Opened the GitHub repository and reviewed all four Java files. |
| Output file in the reviews folder | reviews/parcelpoint-review.md |

Table 2. All findings on the starter code

| No. | File:line | Finding | Found by (SonarQube, AI or both) | SonarQube rule or course term | Correct? (yes or no, and why) |
| --- | --- | --- | --- | --- | --- |
| 1 | Main.java:9,15,17,22 | Uses System.out instead of a logger | SonarQube | S106 | Yes (System.out is used for output.) |
| 2 | ParcelService.java:6 | Unused Date import | Both | S1128 | Yes (Date is not used.) |
| 3 | ParcelService.java:12 | Unused maxParcels field | Both | S1068 | Yes (the field is not read.) |
| 4 | ParcelService.java:30,67,69 | Uses System.out instead of a logger | SonarQube | S106 | Yes (System.out is used for output.) |
| 5 | ParcelService.java:41,42 | Compares Strings using == | Both | S4973 | Yes (== compares references rather than String values.) |
| 6 | ParcelService.java:73-76 | FileWriter is not closed | Both | S2095 | Yes (no resource closing is shown.) |
| 7 | ParcelService.java:79,80 | Empty catch block hides errors | Both | S108 | Yes (IOException is silently ignored.) |
| 8 | Parcel.java:13, ParcelService.java:28,43,67,77 | Hard-coded status strings | Copilot | Magic strings | Yes (parcel states are represented by string literals across classes.) |
| 9 | Parcel.java:7 | Public mutable status field | Copilot | Encapsulation | Yes (other classes can modify status directly.) |
| 10 | ParcelService.java:20–22 | Returns internal lockers list | Copilot | Encapsulation | Yes (callers can modify the internal list.) |
| 11 | Main.java:19, ParcelService.java:63–70 | Boolean flag controls print behavior | Copilot | Control coupling | Yes (Main passes a boolean to select behavior in ParcelService.) |
| 12 | Main.java:6–28 | Main handles input, commands and output | Copilot | Procedural cohesion | Yes (multiple command-handling steps are combined.) |
| 13 | ParcelService.java:24–36 | storeParcel mixes locker selection, messaging and persistence | Copilot | Procedural cohesion | Yes (multiple responsibilities are handled together.) |
| 14 | ParcelService.java:38–49 | pickUp mixes parcel lookup, state changes, persistence and fee calculation | Copilot | Procedural cohesion | Yes (these operations occur in one method.) |
| 15 |ParcelService.java:9–82 | ParcelService has multiple responsibilities | Copilot | Weak class cohesion | Yes (business logic, persistence and console output are combined.) |
| 16 |Parcel.java:7,13, ParcelService.java:28,43,67,77 | Shared status string conventions across classes | Copilot | Connascence of convention | Yes (both classes depend on the same status values, although the precise connascence classification is debatable.) |

**2.1 Name two problems that only the AI review found.** <br>
    1. `Parcel.status` is public, so other classes can change the parcel status directly without validation.<br>
    2. `getLockers()` returns the internal list, allowing other classes to modify the lockers directly.<br>
    
**2.2 Name one problem that only SonarQube found, or write none.**<br>
    SonarQube found the use of `System.out` instead of a logger. The AI review did not report this issue.<br>

**2.3 Name one AI finding that was wrong or too vague, and explain why. If every finding was right, explain how you checked them.**<br>
    The AI finding about `main()` was too vague. The method handles different commands, but they all have the same purpose.<br>
## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it |
| --- | --- | --- |
| 1 | Kept | Console output is needed to show messages to the user.|
| 2 | Kept | Console output is needed to show messages to the user.|
| 3 | Kept | Console output is needed to show messages to the user.|
| 4 | Kept | Console output is needed to show messages to the user.|
| 5 | Fixed | Removed unused import.|
| 6 | Fixed | Removed unused field.|
| 7 | Kept | Console output is needed to show messages to the user.|
| 8 | Fixed | Used .equals() to compare Strings.|
| 9 | Kept | Console output is needed to show messages to the user.|
| 10 | Kept | Console output is needed to show messages to the user.|
| 11 | Fixed | Used try-with-resources to close FileWriter.|
| 12 | Fixed | Added exception handling instead of an empty catch block.|

Number of SonarQube issues that remain after your fixes: 7

The two design problems that you fixed

| Design problem (course term) | What you changed | Commit |
| --- | --- | --- |
| Public mutable field | Changed Parcel.status from public to private and added getter and setter methods. | edfe939 |
| Mutable internal collection | Changed getLockers() to return an unmodifiable list so other classes cannot add or remove lockers directly. | 9ada758 |

Output of the program after your fixes, for the commands `store P1 6501 2`, `pickup P1 5` and `staff`, and the content of parcels.csv:

```text
store P1 6501 2
SMS to 6501: parcel P1 is in locker 2
Locker 2
pickup P1 5
Fee: 30.0
staff
P1 6501 PICKED_UP
Free lockers: 3
quit

Content of parcels.csv:
P1,PICKED_UP
```

## Task 4. AI-generated poor and good designs

| Item | bikeshare-poor | bikeshare-good |
| --- | --- | --- |
| Date | 2026-10-25 | 2026-10-25 |
| AI tool and exact model | GitHub Copilot - Auto(mai-code-1.1-flash) | GitHub Copilot - Auto(mai-code-1.1-flash) |
| Prompt (B or C as given, or your changed prompt in full) | B as given | C as given |
| Builds with mvn package (yes or no) | Yes | Yes |
| Number of classes, interfaces, enums and records | 2 classes (Main, Main.Bike), 0 interfaces, 0 enums, 0 records |  7 classes, 1 interface, 1 enum, 0 records |
| SonarQube issues, total | 20 | 12 |
| SonarQube issues, by rule | S2386: 3, S3776: 1, S135: 1, S106: 9, S7467: 1, S1104: 5 | S3776: 1, S135: 1, S106: 9, S7467: 1 |
| AI review findings in Check 1 | 4 findings: long main method, general exception handling, public collections, and public Bike fields. | 3 findings: bike not moved between stations correctly, long console method, and receipt using CSV text. |
| AI review findings in Check 2 | 3 findings: god class, procedural cohesion, and connascence. | 3 findings: procedural cohesion, content coupling, and connascence. |
| Review output file in the reviews folder | reviews/bikeshare-poor-review.md | reviews/bikeshare-good-review.md |

**4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.** <br>
No. SonarQube did not directly report cohesion or coupling problems. However, it found related issues such as complex code and public fields.<br>

**4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good.**<br>
Yes. The AI review found that bikeshare-poor puts too many tasks in the Main class, such as handling commands, managing bikes, calculating fees, and saving files. In bikeshare-good, these tasks are separated into different classes.<br>

**4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?** <br>
I would choose bikeshare-good because it is easier to update. It uses an enum for member types, so I can add a visitor with a fee of 4 baht per minute and update the input handling.<br>

## Task 5. Reflection

**5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.**<br>
SonarQube checks code problems such as unused fields, public fields, and complex methods. For example, it found unused code and incorrect string comparison in ParcelPoint. However, it did not directly identify cohesion and coupling problems like the god class in bikeshare-poor.<br>

**5.2 When should you not trust the AI review? Give an example from this lab.**<br>
I should not trust the AI review without checking the code myself because AI can make mistakes. For example, the AI review found that bikeshare-good did not move bikes between station lists correctly.

**5.3 What will you do differently in Project Phase 2 after this lab?**<br>
I will use SonarQube and AI review to check my code and design. I will also try to separate tasks into different classes to make the code easier to understand and update. <br>

## AI usage declaration

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| GitHub Copilot - Auto(mai-code-1.1-flash) | Generate poor and good BikeShare programs using Prompt B and Prompt C. | bikeshare-poor and bikeshare-good | Built both projects with Maven, tested renting and returning bikes, and checked SonarQube issues. |
| GitHub Copilot - Auto(mai-code-1.1-flash) | Review code quality and design quality using Prompt A. | reviews | Compared the findings with the Java code and SonarQube results. |
| ChatGPT (GPT-6)| Help fix some code, find mistakes I missed and explain the lab. | parcelpoint/  | Checked the code changes, ran Maven, tested the program, and reviewed the report. |
