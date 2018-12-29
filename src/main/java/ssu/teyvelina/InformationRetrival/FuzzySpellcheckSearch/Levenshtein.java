package ssu.teyvelina.InformationRetrival.FuzzySpellcheckSearch;

/*public class Levenshtein {
}
package ssu.teyvelina.informationretrival.search;*/

public class Levenshtein {
    private int[] currentRow;
    private int[] previousRow;
    private int[] transpositionRow;

    public Levenshtein(int maxLength){
        currentRow = new int[maxLength + 1];
        previousRow = new int[maxLength + 1];
        transpositionRow = new int[maxLength + 1];
    }

    public int getDistace(String first, String second, int max) {
        int firstLength = first.length();
        int secondLength = second.length();

        if(firstLength == 0)
            return secondLength;
        else if(secondLength == 0)
            return firstLength;

        if(firstLength > secondLength){
            String tmp = first;
            first = second;
            second = tmp;
            firstLength = first.length();
            secondLength = second.length();
        }

        if (max < 0)
            max = secondLength;
        if (secondLength - firstLength > max)
            return max + 1;

        if (firstLength > currentRow.length){
            currentRow = new int[firstLength + 1];
            previousRow = new int[firstLength + 1];
            transpositionRow = new int[firstLength + 1];
        }

        // заполняем от 0 до n+1
        for (int i = 0; i <= firstLength; i++)
            previousRow[i] = i;

        char lastSecondChar = 0;
        for (int i = 1; i <= secondLength; i++){
            char secondChar = second.charAt(i - 1);
            currentRow[0] = i;

            // Вычисляем только диагональную полосу шириной 2 * (max + 1)
            int from = Math.max(i - max - 1, 1);
            int to = Math.min(i + max + 1, firstLength);

            char lastFirstChar = 0;
            for (int j = from; j <= to; j++){
                char firstChar = first.charAt(j - 1);

                // Вычисляем минимальную цену перехода в текущее состояние
                // из предыдущих среди удаления, вставки и замены соответственно.
                int cost;
                if (firstChar == secondChar)
                    cost = 0;
                else
                    cost = 1;
                int value = Math.min(Math.min(currentRow[j - 1] + 1, previousRow[j] + 1),
                        previousRow[j - 1] + cost);
                // Если вдруг была транспозиция, надо также учесть и её стоимость.
                if (firstChar == lastSecondChar && secondChar == lastFirstChar)
                    value = Math.min(value, transpositionRow[j - 2] + cost);

                currentRow[j] = value;
                lastFirstChar = firstChar;
            }
            lastSecondChar = secondChar;

            int tempRow[] = transpositionRow;
            transpositionRow = previousRow;
            previousRow = currentRow;
            currentRow = tempRow;
        }
        return previousRow[firstLength];
    }
}