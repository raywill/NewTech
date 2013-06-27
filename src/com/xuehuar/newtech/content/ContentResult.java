package com.xuehuar.newtech.content;

public class ContentResult {
	public class Material {
		public String mMaterial;
		Material(String material) {
			mMaterial = material;
		}
	}
	
	public class Quiz {
		private String mQuiz;
	}
	
	public class Lesson{
		public int mStatus;
		public String mMaterialName;
		public Material mMaterial;
		public Quiz [] mQuiz;
		public Lesson() {
			mStatus = 0;
		}
		public void setMaterial(String materialName, String material) {
			mMaterialName = materialName;
			mMaterial = new Material(material);
		}
		public void setQuiz(Quiz[] quiz) {
			mQuiz = quiz;
		}
		public void setStatus(int status) {
			mStatus = status;
		}
		
	}
		
	public class Chapter {		
		public Lesson [] mLesson;
		public String mChapterName;
		public void setLesson(Lesson[] lesson) {
			mLesson = lesson;
		}
		public void setChapterName(String name) {
			mChapterName = name;			
		}
	}
	

	public Chapter [] mChapter;
	
	final int mChapterCount = 3;
	final int mLessonCountEachChapter = 5;
	private String[] mChapterNames = new String[] {
			"Chapter 1", "Chapter 2", "Chapter 3"
	};
	private String[][] mLessonNames = new String [][] {
			{"Hello", "World", "University", "School", "Home"},
			{"Dream", "Reality", "Home", "Money", "Programming"},
			{"Good", "Bad", "Better", "Best", "Perfect"}
	};
	private int[][] mLessonStatus = new int [][] {
			{3,3,3,3,3},
			{2,1,1,1,1},
			{1,1,1,1,1},
	};
	private String[][] mMaterial = new String [][] {
			{"Hello，you know?", "World，you know?", "University，you know?", "School，you know?", "Home，you know?"},
			{"Dream", "Reality", "Home", "Money", "Programming"},
			{"Good", "Bad", "Better", "Best", "Perfect"}
	};	

		
	public ContentResult() {
		mChapter = new Chapter[mChapterCount];
		for (int chapter_idx = 0; chapter_idx < mChapterCount; chapter_idx++) {
			mChapter[chapter_idx] = new Chapter();
			Lesson[] lesson = new Lesson[mLessonCountEachChapter];
			mChapter[chapter_idx].setLesson(lesson);
			mChapter[chapter_idx].setChapterName(mChapterNames[chapter_idx]);						
			for (int lesson_idx = 0; lesson_idx < mLessonCountEachChapter; lesson_idx++) {
				lesson[lesson_idx] = new Lesson();
				lesson[lesson_idx].setMaterial(
						mLessonNames[chapter_idx][lesson_idx], 
						mMaterial[chapter_idx][lesson_idx]);
				lesson[lesson_idx].setStatus(mLessonStatus[chapter_idx][lesson_idx]);
			}			
		}		
	}
}
