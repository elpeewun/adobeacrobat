/*
1. $ imp: export HADOOP_CLASSPATH = $(hadoop classpath)
2. $ echo $HADOOP_CLASSPATH
3. $ imp: hadoop -fs -mkdir /WordCount
4. $ imp: hadoop -fs -mkdir /WordCount/Input
5. $ imp: hadoop fs -put /WordCountLocal/InputLocal/*.txt /WordCount/Input
6. $ cd /WordCountLocal
7. $ javac -cp $(HADOOP_CLASSPATH) -d /localClassFiles WordCount.java
8. $ jar -cvf first.jar -C /localClassFiles
9. $ hadoop jar /first.jar WordCount /WordCount/Input /WordCount/Output
10. $ hadoop dfs -cat /WordCount/Output/*
11. $
12. $
*/

/*
1.  bin/hadoop namenode -format

2.  bin/hdfs namenode

3.  sbin/start-all.sh

4. jps

5.  hdfs dfs -mkdir  /input

6.  hdfs dfs -put  *.txt  /input

7.  hdfs dfs -ls *.txt /input

8.  hadoop com.sun.tools.javac.Main WordCount.java

9. jar cf wc.jar WordCount*.class

10.  hadoop jar wc.jar  WordCount   /input     /output

11.  hdfs dfs -cat /output/part-r-00000

You Can see output on web interface:

localhost:9870
Utilities-> Browse the filesystem
*/
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount 
{

    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable>
    {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
        {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) 
            {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text,IntWritable,Text,IntWritable> 
    {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException 
        {
            int sum = 0;
            for (IntWritable val : values) 
            {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception 
    {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
      }
}