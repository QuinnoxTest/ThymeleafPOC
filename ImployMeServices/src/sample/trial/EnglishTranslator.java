package sample.trial;

public class EnglishTranslator implements ITranslator {

	@Override
	public int getLanguageAlphabetCount() {
		System.out.println("EnglishTranslator");
		return 26;
	}

	@Override
	public boolean checkValidAlphabet() {
		return true;
	}

}
