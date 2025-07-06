# 🎓 Quiz Master Pro – Java Swing Based Quiz Application

Quiz Master Pro is an interactive and feature-rich desktop quiz application built using **Java Swing**. It supports multiple quiz categories, score tracking, leaderboards, per-question timers, and a modern UI design. Perfect for learning, practicing, or just testing your knowledge!

---

## 🚀 Features

- 👤 **User Login & Registration** with password protection
- 🌗 **Dark Mode** toggle for a modern interface
- 🧠 **Multiple Quiz Categories**: Java, General Knowledge, Mathematics
- ⏳ **Per-Question Timer** with timeout buzzer
- 🧪 **50-50 Lifeline** support
- 🧾 **Score History Tracking** (file-based)
- 🏆 **Leaderboard** showing top scorers
- 🔁 **Rotating Question Sets** to prevent repetition
- 📁 **File-Based Data Management** (No SQL required)
- 📤 **Score Export Support** (CSV/PDF planned)
- 📊 **Admin Panel** (Planned) for uploading questions
- 🧑‍🎓 **User Profiles** with attempt logs
- ✨ Gamification support (badges, levels - optional)

---

## 🖥️ Screenshots

> Add screenshots here of:
> - Login Page
> - Quiz Interface
> - Score Summary
> - Score History
> - Leaderboard

---

## 📂 Project Structure

```
QuizApplication/
│
├── data/
│   ├── score_history.txt
│   └── leaderboard.txt
│
├── icons/
│   ├── quiz.jpg
│   ├── score.png
│   └── timeout.wav
│
├── Login.java
├── Register.java
├── Dashboard.java
├── Quiz.java
├── Score.java
├── ScoreHistoryWriter.java
├── Leaderboard.java
└── SoundUtils.java
```

---

## 🛠️ Technologies Used

- Java (JDK 8+)
- Java Swing (GUI)
- AWT
- File I/O (for persistence)
- Basic OOP concepts

---

## ⚙️ How to Run

1. **Clone the Repository**  
   ```
   git clone https://github.com/raahulpandey/Quiz-Application-using-java-swing.git
   cd Quiz-Application-using-java-swing
   ```

2. **Compile all files**  
   ```
   javac *.java
   ```

3. **Run the application**  
   ```
   java Login
   ```

> Make sure your `icons` and `data` folders are in the correct directory before running.

---

## ✅ To-Do / Improvements

- [ ] Admin panel for uploading question sets
- [ ] Score export to CSV or PDF
- [ ] Profile management UI
- [ ] More themes (dark/light/custom)
- [ ] Online multiplayer quiz mode (optional)

---

## 📬 Feedback & Contributions

Feel free to fork the repo, raise issues, or submit pull requests.  
**Star ⭐** the repo if you liked it!

---

## 👨‍💻 Author

**Rahul Pandey**  
📧 arryaroy7367@gmail.com  
🔗 [GitHub Profile](https://github.com/raahulpandey/)

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
