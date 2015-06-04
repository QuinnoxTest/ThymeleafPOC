package sample.trial;

public class SpanishTranslator implements ITranslator {

	@Override
	public int getLanguageAlphabetCount() {
		System.out.println("SpanishTranslator");
		return 0;
	}

	@Override
	public boolean checkValidAlphabet() {
		return true;
	}

}
