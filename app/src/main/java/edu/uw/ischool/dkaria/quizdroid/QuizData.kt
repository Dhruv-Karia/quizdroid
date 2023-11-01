package edu.uw.ischool.dkaria.quizdroid

data class Question(
    val questionText: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

data class Topic(
    val topicName: String,
    val description: String,
    val questions: List<Question>
)

val topics = listOf(
    Topic(
        topicName = "Math",
        description = "This is a Math quiz.",
        questions = listOf(
            Question(
                questionText = "What is 2 + 2?",
                answers = listOf("1", "2", "3", "4"),
                correctAnswerIndex = 3
            ),
            Question(
                questionText = "What is the square root of 16?",
                answers = listOf("2", "3", "4", "5"),
                correctAnswerIndex = 2
            ),
            Question(
                questionText = "What is 5 * 5?",
                answers = listOf("10", "15", "20", "25"),
                correctAnswerIndex = 3
            )
        )
    ),
    Topic(
        topicName = "Physics",
        description = "This is a Physics quiz.",
        questions = listOf(
            Question(
                questionText = "What is the speed of light?",
                answers = listOf("299,792 km/s",
                    "150,000 km/s",
                    "1,000 km/s",
                    "500,000 km/s"),
                correctAnswerIndex = 0
            ),
            Question(
                questionText = "Who is known as the father of Physics?",
                answers = listOf("Albert Einstein",
                    "Isaac Newton",
                    "Nikola Tesla",
                    "Galileo Galilei"),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "What is the force of gravity on Earth?",
                answers = listOf("9.8 m/s^2",
                    "9.8 km/s^2",
                    "10 m/s^2",
                    "10 km/s^2"),
                correctAnswerIndex = 0
            )
        )
    ),
    Topic(
        topicName = "Marvel Super Heroes",
        description = "This is a Marvel Super Heroes quiz.",
        questions = listOf(
            Question(
                questionText = "Who is Iron Man?",
                answers = listOf("Tony Stark",
                    "Steve Rogers",
                    "Bruce Banner",
                    "Peter Parker"),
                correctAnswerIndex = 0
            ),
            Question(
                questionText = "Who does Chris Hemsworth play in Marvel movies?",
                answers = listOf("Iron Man",
                    "Captain America",
                    "Thor",
                    "Hulk"),
                correctAnswerIndex = 2
            ),
            Question(
                questionText = "What is the real name of Captain America?",
                answers = listOf("Tony Stark",
                    "Steve Rogers",
                    "Bruce Banner",
                    "Peter Parker"),
                correctAnswerIndex = 1
            )
        )
    ),
    Topic(
        topicName = "History",
        description = "This is a History quiz.",
        questions = listOf(
            Question(
                questionText = "Who was the first president of the United States?",
                answers = listOf("George Washington",
                    "Thomas Jefferson",
                    "Abraham Lincoln",
                    "John Adams"),
                correctAnswerIndex = 0
            ),
            Question(
                questionText = "In what year did World War II end?",
                answers = listOf("1945",
                    "1944",
                    "1943",
                    "1942"),
                correctAnswerIndex = 0
            ),
            Question(
                questionText = "Who discovered America?",
                answers = listOf("Christopher Columbus",
                    "Amerigo Vespucci",
                    "Leif Erikson",
                    "Ferdinand Magellan"),
                correctAnswerIndex = 0
            )
        )
    ),
    Topic(
        topicName = "Science",
        description = "This is a Science quiz.",
        questions = listOf(
            Question(
                questionText = "What is the chemical symbol for water?",
                answers = listOf("H2O",
                    "CO2",
                    "O2",
                    "N2"),
                correctAnswerIndex = 0
            ),
            Question(
                questionText = "What is the chemical symbol for gold?",
                answers = listOf("Gd",
                    "Au",
                    "Ag",
                    "Ga"),
                correctAnswerIndex = 1
            ),
            Question(
                questionText = "What is the third planet from the sun?",
                answers = listOf("Mars",
                    "Venus",
                    "Earth",
                    "Mercury"),
                correctAnswerIndex = 2
            )
        )
    )
)