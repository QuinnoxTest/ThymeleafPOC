package sample.trial;

public class FrenchTranslator implements ITranslator {

	@Override
	public int getLanguageAlphabetCount() {
		System.out.println("FrenchTranslator");
		return 26;
	}

	@Override
	public boolean checkValidAlphabet() {
		return true;
	}

}
