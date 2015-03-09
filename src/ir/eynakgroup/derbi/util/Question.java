package ir.eynakgroup.derbi.util;

public class Question {
	private String question;
	private String answer;
	private String[] falseChoice;

	public Question(String question, String answer, String[] falses) {
		this.setQuestion(question);
		this.setAnswer(answer);
		this.falseChoice = new String[3];
		for (int i = 0; i < falseChoice.length; i++)
			falseChoice[i] = falses[i];
	}

	public Question(String[] qanda) {
		setQuestion(qanda[0]);
		setAnswer(qanda[1]);
		falseChoice = new String[3];
		for (int i = 0; i < falseChoice.length; i++)
			falseChoice[i] = qanda[i + 2];
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String[] getFalseChoice() {
		return falseChoice;
	}

	public void setFalseChoice(String[] falses) {
		falseChoice = new String[3];
		for (int i = 0; i < falseChoice.length; i++)
			falseChoice[i] = falses[i];
	}
	@Override
	public String toString() {
		return question + "??" + answer + "!{" + falseChoice[0]+"," + falseChoice[1]+"," + falseChoice[2]+"}";
	}
}
