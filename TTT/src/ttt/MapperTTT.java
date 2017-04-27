/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author chiragmudgal
 */
public class MapperTTT extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable ONE = new IntWritable(1);
    private Text word = new Text();

    @Override
    public void map(LongWritable inputKey, Text inputVal, Mapper.Context context) throws IOException, InterruptedException {

        JSONParser parser = new JSONParser();
        String line;
        StringTokenizer tokenizer;

        try {
            //<----Parse the twitter data---->
            JSONArray a = (JSONArray) parser.parse('[' + inputVal.toString() + ']');

            for (Object o : a) {

                JSONObject tweet = (JSONObject) o;
                line = (String) tweet.get("text");
                tokenizer = new StringTokenizer(line);

                while (tokenizer.hasMoreTokens()) {

                    word.set(tokenizer.nextToken());

                    //<----Check if the word is equal to the keyword we are looking for--->
                    if (word.toString().toUpperCase().equals("MICROSOFT")
                            || word.toString().toUpperCase().equals("GOOGLE")
                            || word.toString().toUpperCase().equals("FACEBOOK")
                            || word.toString().toUpperCase().equals("AMAZON")) {

                        word.set(word.toString().toUpperCase());
                        context.write(word, ONE);
                    }
                }

                tokenizer = null;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
