package com.aarush.tensorflow.audio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.aarush.tensorflow.classifiers.models.cifar10.Cifar10AudioClassifier;

public class Demo {
	public static void main(String[] args) {
		System.out.println("\n\nCreating a classifier..");

		Cifar10AudioClassifier classifier = new Cifar10AudioClassifier();
		
		try {
			System.out.println("Loading the machine learning model..");
			classifier.load_model();	
			System.out.println("Model Loaded..");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Reading audio files..");
		// after shuffling result is same always
		List<String> paths = getAudioFiles();
		System.out.println("Audio files read..");

		// shuffling the songs in the lists 
		Collections.shuffle(paths);

		for (String path : paths) {
			System.out.println("\n\nPredicting the genre of song at location: " + path + " ...");
			File f = new File(path);
			String label = classifier.predict_audio(f);
			System.out.println();
			System.out.println("*********************************");
			System.out.println("Predicted Genre: " + label);
			System.out.println("*********************************");
		}
	}

	private static List<String> getAudioFiles() {
		List<String> list = new ArrayList<String>();
		list.add("C:\\Users\\mehav\\Downloads\\jazz.mp3");
		list.add("C:\\Users\\mehav\\Downloads\\song.mp3");

		return list;
	}
}
