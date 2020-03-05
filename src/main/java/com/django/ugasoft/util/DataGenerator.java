package com.django.ugasoft.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public final class DataGenerator {

  private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
  private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String DIGITS = "0123456789";
  private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
  private boolean useLower;
  private boolean useUpper;
  private boolean useDigits;
  private boolean usePunctuation;

  private DataGenerator() {
    throw new UnsupportedOperationException("Empty constructor is not supported.");
  }

  private DataGenerator(DataGeneratorBuilder builder) {
    this.useLower = builder.useLower;
    this.useUpper = builder.useUpper;
    this.useDigits = builder.useDigits;
    this.usePunctuation = builder.usePunctuation;
  }

  public static class DataGeneratorBuilder {

    private boolean useLower;
    private boolean useUpper;
    private boolean useDigits;
    private boolean usePunctuation;

    public DataGeneratorBuilder() {
      this.useLower = false;
      this.useUpper = false;
      this.useDigits = false;
      this.usePunctuation = false;
    }

    /**
     * Set true in case you would like to include lower characters
     * (abc...xyz). Default false.
     *
     * @param useLower true in case you would like to include lower
     *         characters (abc...xyz). Default false.
     * @return the builder for chaining.
     */
    public DataGeneratorBuilder useLower(boolean useLower) {
      this.useLower = useLower;
      return this;
    }

    /**
     * Set true in case you would like to include upper characters
     * (ABC...XYZ). Default false.
     *
     * @param useUpper true in case you would like to include upper
     *         characters (ABC...XYZ). Default false.
     * @return the builder for chaining.
     */
    public DataGeneratorBuilder useUpper(boolean useUpper) {
      this.useUpper = useUpper;
      return this;
    }

    /**
     * Set true in case you would like to include digit characters (123..).
     * Default false.
     *
     * @param useDigits true in case you would like to include digit
     *         characters (123..). Default false.
     * @return the builder for chaining.
     */
    public DataGeneratorBuilder useDigits(boolean useDigits) {
      this.useDigits = useDigits;
      return this;
    }

    /**
     * Set true in case you would like to include punctuation characters
     * (!@#..). Default false.
     *
     * @param usePunctuation true in case you would like to include
     *            punctuation characters (!@#..). Default false.
     * @return the builder for chaining.
     */
    public DataGeneratorBuilder usePunctuation(boolean usePunctuation) {
      this.usePunctuation = usePunctuation;
      return this;
    }

    /**
     * Get an object to use.
     *
     * @return the object.
     */
    public DataGenerator build() {
      return new DataGenerator(this);
    }
  }

  /**
   * This method will generate a randomData depending the use* properties you
   * define. It will use the categories with a probability. It is not sure
   * that all of the defined categories will be used.
   *
   * @param length the length of the randomData you would like to generate.
   * @return a randomData that uses the categories you define when constructing
   * the object with a probability.
   */
  public String generate(int length) {
    // Argument Validation.
    if (length <= 0) {
      return "";
    }

    // Variables.
    StringBuilder randomData = new StringBuilder(length);
    Random random = new Random(System.nanoTime());

    // Collect the categories to use.
    List<String> charCategories = new ArrayList<>(4);
    if (useLower) {
      charCategories.add(LOWER);
    }
    if (useUpper) {
      charCategories.add(UPPER);
    }
    if (useDigits) {
      charCategories.add(DIGITS);
    }
    if (usePunctuation) {
      charCategories.add(PUNCTUATION);
    }

    // Build the data.
    for (int i = 0; i < length; i++) {
      String charCategory = charCategories.get(random.nextInt(charCategories.size()));
      int position = random.nextInt(charCategory.length());
      randomData.append(charCategory.charAt(position));
    }
    return new String(randomData);
  }
}

