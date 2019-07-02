import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Assignment1 {

    public static void main(String[] args) throws IOException {

        BufferedWriter output = null;
        try {
            File file = new File("output.txt");
            output = new BufferedWriter(new FileWriter(file));

            int[] inputSizes = {10000, 20000, 40000, 60000, 80000};

            String radixResult = radixCalculation(inputSizes);
            String selectResult = selectionCalculation(inputSizes);
            String insertionResult = insertionCalculation(inputSizes);
            String mergeResult = mergeCalculation(inputSizes);
            String binaryResult = binarySearchCalculation(inputSizes);

            output.write(radixResult + "\n\n");
            output.write(selectResult + "\n\n");
            output.write(insertionResult + "\n\n");
            output.write(mergeResult + "\n\n");
            output.write(binaryResult + "\n\n");

            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    /**
     * Generates random numbers with respect to input value and return the array
     * containing all the numbers.
     *
     * @param dataSize the number of random numbers will be generated
     * @return numbers - the array containing the random numbers
     */
    static int[] generateNumbers(int dataSize) {
        int[] numbers = new int[dataSize];
        Random rand = new Random();

        for (int i = 0; i < dataSize; i++) {
            numbers[i] = (rand.nextInt(dataSize * 10) + 1);
        }

        return numbers;
    }

    /**
     * Reverses an array. It is used for calculating the worst case of some
     * algorithms.
     *
     * @param arr - the array will be reversed
     */
    private static void reverseArray(int arr[]) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    /**
     * Executes 'Radix Sort' for arrays which have different sizes and calculates
     * execution times for worst, average, and best cases of 'Radix Sort'. It
     * iterates the calculation ten times and takes average to reduce the noisy data
     * in the calculation.
     *
     * @param inputSizes - the array which contains the sizes of the arrays which
     *                   will be sorted
     * @return the string which contains the sizes of the arrays and their
     * corresponding execution times
     */
    static String radixCalculation(int[] inputSizes) {
        String radixWorst = "begin worst radix\n";
        String radixAvr = "begin avr radix\n";
        String radixBest = "begin best radix\n";
        RadixSort.sort(inputSizes);

        for (int inputSize : inputSizes) {
            int count = 5;
            long execTimeWorst = 0;
            int execTimeAvg = 0;
            int execTimeBest = 0;
            int[] randNumbersWorst = genNumForRadixWorst(inputSize);
            int[] randNumbersAvg = genNumForRadixAvg(inputSize);
            int[] randNumbersBest = genNumForRadixBest(inputSize);

            while (count > 0) {
                /** Calculation of the worst case */
                long startTimeWorst = System.nanoTime();
                RadixSort.sort(randNumbersWorst);
                long endTimeWorst = System.nanoTime();

                execTimeWorst += (endTimeWorst - startTimeWorst);

                /** Calculation of the average case */
                long startTimeAvg = System.nanoTime();
                RadixSort.sort(randNumbersAvg);
                long endTimeAvg = System.nanoTime();

                execTimeAvg += (endTimeAvg - startTimeAvg);

                /** Calculation of the best case */
                long startTimeBest = System.nanoTime();
                RadixSort.sort(randNumbersBest);
                long endTimeBest = System.nanoTime();

                execTimeBest += (endTimeBest - startTimeBest);

                count--;
            }
            radixWorst = radixWorst.concat(execTimeWorst / 5000 + "\n");
            radixAvr = radixAvr.concat(execTimeAvg / 5000 + "\n");
            radixBest = radixBest.concat(execTimeBest / 5000 + "\n");
        }
        radixWorst = radixWorst.concat("end");
        radixAvr = radixAvr.concat("end");
        radixBest = radixBest.concat("end");

        return radixWorst + "\n\n" + radixAvr + "\n\n" + radixBest;
    }

    /**
     * Executes 'Selection Sort' for arrays which have different sizes and
     * calculates execution times for worst, average, and best cases of 'Selection
     * Sort'. It iterates the calculation ten times and takes average to reduce the
     * noisy data in the calculation.
     *
     * @param inputSizes - the array which contains the sizes of the arrays which
     *                   will be sorted
     * @return the string which contains the sizes of the arrays and their
     * corresponding execution times
     */
    private static String selectionCalculation(int[] inputSizes) {
        String selectionWorst = "begin worst selection\n";
        String selectionAvr = "begin avr selection\n";
        String selectionBest = "begin best selection\n";
        SelectionSort.sort(inputSizes);

        for (int inputSize : inputSizes) {
            int count = 5;
            long execTimeWorstSelection = 0;
            long execTimeAvgSelection = 0;
            long execTimeBestSelection = 0;
            int[] randNumbers;
            randNumbers = generateNumbers(inputSize);
            int[] randNumbersAvg = randNumbers.clone();
            reverseArray(randNumbers);

            while (count > 0) {
                /** Calculation of worse case */
                long startTimeWorst = System.nanoTime();
                SelectionSort.sort(randNumbers);
                long endTimeWorst = System.nanoTime();

                execTimeWorstSelection += (endTimeWorst - startTimeWorst);

                /** Calculation of average case */
                long startTimeAvg = System.nanoTime();
                SelectionSort.sort(randNumbersAvg);
                long endTimeAvg = System.nanoTime();

                execTimeAvgSelection += (endTimeAvg - startTimeAvg);

                /** Calculation of best case */
                long startTimeBest = System.nanoTime();
                SelectionSort.sort(randNumbers);
                long endTimeBest = System.nanoTime();

                execTimeBestSelection += (endTimeBest - startTimeBest);

                count--;
            }
            selectionWorst = selectionWorst.concat(execTimeWorstSelection / 5000 + "\n");
            selectionAvr = selectionAvr.concat(execTimeAvgSelection / 5000 + "\n");
            selectionBest = selectionBest.concat(execTimeBestSelection / 5000 + "\n");
        }
        selectionWorst = selectionWorst.concat("end");
        selectionAvr = selectionAvr.concat("end");
        selectionBest = selectionBest.concat("end");

        return selectionWorst + "\n\n" + selectionAvr + "\n\n" + selectionBest;
    }

    /**
     * Executes 'Insertion Sort' for arrays which have different sizes and
     * calculates execution times for worst, average, and best cases of 'Insertion
     * Sort'. It iterates the calculation ten times and takes average to reduce the
     * noisy data in the calculation.
     *
     * @param inputSizes - the array which contains the sizes of the arrays which
     *                   will be sorted
     * @return the string which contains the sizes of the arrays and their
     * corresponding execution times
     */
    public static String insertionCalculation(int[] inputSizes) {
        String insertionWorst = "begin worst insertion\n";
        String insertionAvr = "begin avr insertion\n";
        String insertionBest = "begin best insertion\n";
        InsertionSort.sort(inputSizes);

        for (int inputSize : inputSizes) {
            int count = 5;
            long execTimeWorst = 0;
            long execTimeAvg = 0;
            long execTimeBest = 0;
            int[] randNumbers;
            randNumbers = generateNumbers(inputSize);
            int[] randNumbersAvg = randNumbers.clone();
            reverseArray(randNumbers);

            while (count > 0) {
                /** Calculation of worse case */
                long startTimeWorst = System.nanoTime();
                InsertionSort.sort(randNumbers);
                long endTimeWorst = System.nanoTime();

                execTimeWorst += (endTimeWorst - startTimeWorst);

                /** Calculation of average case */
                long startTimeAvg = System.nanoTime();
                InsertionSort.sort(randNumbersAvg);
                long endTimeAvg = System.nanoTime();

                execTimeAvg += (endTimeAvg - startTimeAvg);

                /** Calculation of best case */
                long startTimeBest = System.nanoTime();
                InsertionSort.sort(randNumbers);
                long endTimeBest = System.nanoTime();

                execTimeBest += (endTimeBest - startTimeBest);

                count--;
            }

            insertionWorst = insertionWorst.concat(execTimeWorst / 5000 + "\n");
            insertionAvr = insertionAvr.concat(execTimeAvg / 5000 + "\n");
            insertionBest = insertionBest.concat(execTimeBest / 5000 + "\n");
        }
        insertionWorst = insertionWorst.concat("end");
        insertionAvr = insertionAvr.concat("end");
        insertionBest = insertionBest.concat("end");

        return insertionWorst + "\n\n" + insertionAvr + "\n\n" + insertionBest;
    }

    /**
     * Executes 'Merge Sort' for arrays which have different sizes and calculates
     * execution times for worst, average, and best cases of 'Merge Sort'. It
     * iterates the calculation ten times and takes average to reduce the noisy data
     * in the calculation.
     *
     * @param inputSizes - the array which contains the sizes of the arrays which
     *                   will be sorted
     * @return the string which contains the sizes of the arrays and their
     * corresponding execution times
     */
    static String mergeCalculation(int[] inputSizes) {
        String mergeWorst = "begin worst merge\n";
        String mergeAvr = "begin avr merge\n";
        String mergeBest = "begin best merge\n";
        MergeSort.sort(inputSizes, 0, inputSizes.length - 1);

        for (int inputSize : inputSizes) {
            int count = 5;
            long execTimeWorst = 0;
            long execTimeAvg = 0;
            long execTimeBest = 0;
            int[] randNumbers = generateNumbers(inputSize);
            int[] randNumbersAvg = randNumbers.clone();
            WorstCaseMerge.seperate(randNumbers);

            while (count > 0) {
                /** Calculation of worse case */
                long startTimeWorst = System.nanoTime();
                MergeSort.sort(randNumbers, 0, randNumbers.length - 1);
                long endTimeWorst = System.nanoTime();

                execTimeWorst += (endTimeWorst - startTimeWorst);

                /** Calculation of average case */
                long startTimeAvg = System.nanoTime();
                MergeSort.sort(randNumbersAvg, 0, randNumbersAvg.length - 1);
                long endTimeAvg = System.nanoTime();

                execTimeAvg += (endTimeAvg - startTimeAvg);

                /** Calculation of best case */
                long startTimeBest = System.nanoTime();
                MergeSort.sort(randNumbers, 0, randNumbers.length - 1);
                long endTimeBest = System.nanoTime();

                execTimeBest += (endTimeBest - startTimeBest);

                count--;
            }

            mergeWorst = mergeWorst.concat(execTimeWorst / 5000 + "\n");
            mergeAvr = mergeAvr.concat(execTimeAvg / 5000 + "\n");
            mergeBest = mergeBest.concat(execTimeBest / 5000 + "\n");
        }
        mergeWorst = mergeWorst.concat("end");
        mergeAvr = mergeAvr.concat("end");
        mergeBest = mergeBest.concat("end");

        return mergeWorst + "\n\n" + mergeAvr + "\n\n" + mergeBest;
    }

    /**
     * Executes 'Binary Search' for arrays which have different size and calculates
     * execution times for worst, average, and best cases of 'Binary Search'. It
     * iterates the calculation ten times and takes average to reduce the noisy data
     * in the calculation.
     *
     * @param inputSizes - the array which contains the sizes of the arrays which
     *                   will be sorted
     * @return the string which contains the sizes of the arrays and their
     * corresponding execution times
     */
    static String binarySearchCalculation(int[] inputSizes) {
        String binaryWorst = "begin worst binary\n";
        String binaryAvr = "begin avr binary\n";
        String binaryBest = "begin best binary\n";

        for (int inputSize : inputSizes) {
            int count = 5;
            long execTimeWorst = 0;
            long execTimeAvg = 0;
            long execTimeBest = 0;

            int[] randNumbers = generateNumbers(inputSize);
            RadixSort.sort(randNumbers);
            BinarySearch.search(randNumbers, 0, randNumbers.length - 1, 100000000);

            while (count > 0) {

                /** Calculation of worse case */
                int arrLen = randNumbers.length;
                int mid = (arrLen - 1) / 2;

                long startTimeWorst = System.nanoTime();
                BinarySearch.search(randNumbers, 0, randNumbers.length - 1, 100000000);
                long endTimeWorst = System.nanoTime();

                execTimeWorst += (endTimeWorst - startTimeWorst);

                /** Calculation of average case */
                Random rand = new Random();
                int currentIndex = rand.nextInt(randNumbers.length);

                long startTimeAvg = System.nanoTime();
                BinarySearch.search(randNumbers, 0, randNumbers.length - 1, randNumbers[currentIndex]);
                long endTimeAvg = System.nanoTime();

                execTimeAvg += (endTimeAvg - startTimeAvg);

                /** Calculation of best case */
                long startTimeBest = System.nanoTime();
                BinarySearch.search(randNumbers, 0, randNumbers.length - 1, randNumbers[mid]);
                long endTimeBest = System.nanoTime();

                execTimeBest += (endTimeBest - startTimeBest);

                count--;
            }

            binaryWorst = binaryWorst.concat(execTimeWorst / 5 + "\n");
            binaryAvr = binaryAvr.concat(execTimeAvg / 5 + "\n");
            binaryBest = binaryBest.concat(execTimeBest / 5 + "\n");
        }
        binaryWorst = binaryWorst.concat("end");
        binaryAvr = binaryAvr.concat("end");
        binaryBest = binaryBest.concat("end");

        return binaryWorst + "\n\n" + binaryAvr + "\n\n" + binaryBest;
    }

    /**
     * Generates numbers and creates an array, containing them for the worst case of
     * the Radix Sort algorithm. It generates numbers which have more digits.
     *
     * @param dataSize - the number which is the size of the array, will be created
     * @return the array for the worst case of the Radix Sort algorithm.
     */
    static int[] genNumForRadixWorst(int dataSize) {
        int[] numbers = new int[dataSize];
        Random rand = new Random();

        for (int i = 0; i < dataSize; i++) {
            numbers[i] = (rand.nextInt(dataSize * 1000) + 1);
        }

        return numbers;
    }

    /**
     * Generates numbers and creates an array, containing them for the average case
     * of the Radix Sort algorithm. It generates numbers which have more digits.
     *
     * @param dataSize - the number which is the size of the array, will be created
     * @return the array for the average case of the Radix Sort algorithm.
     */
    static int[] genNumForRadixAvg(int dataSize) {
        int[] numbers = new int[dataSize];
        Random rand = new Random();

        for (int i = 0; i < dataSize; i++) {
            numbers[i] = (rand.nextInt(dataSize * 100) + 1);
        }

        return numbers;
    }

    /**
     * Generates numbers and creates an array, containing them for the best case of
     * the Radix Sort algorithm. It generates numbers which have more digits.
     *
     * @param dataSize - the number which is the size of the array, will be created
     * @return the array for the best case of the Radix Sort algorithm.
     */
    static int[] genNumForRadixBest(int dataSize) {
        int[] numbers = new int[dataSize];
        Random rand = new Random();

        for (int i = 0; i < dataSize; i++) {
            numbers[i] = (rand.nextInt(dataSize) + 1);
        }

        return numbers;
    }
}
