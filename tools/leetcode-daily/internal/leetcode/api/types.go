package api

type TodayQuestion struct {
	TodayRecord []*struct {
		Date       string `graphql:"date" json:"date"`             // 当天日期
		UserStatus string `graphql:"userStatus" json:"userStatus"` // 用户的完成状态(FINISH)
		Question   *struct {
			Id                 string `graphql:"questionId,alias:id" json:"id"`
			TitleSlug          string `graphql:"titleSlug" json:"titleSlug"`
			Title              string `graphql:"title" json:"title"`
			TranslatedTitle    string `graphql:"translatedTitle" json:"translatedTitle"`
			QuestionFrontendId string `graphql:"questionFrontendId" json:"questionFrontendId"`
			PaidOnly           bool   `graphql:"isPaidOnly,alias:paidOnly" json:"paidOnly"`
			Difficulty         string `graphql:"difficulty" json:"difficulty"`
			TopicTags          []*struct {
				Name           string `graphql:"name" json:"name"`
				Slug           string `graphql:"slug" json:"slug"`
				NameTranslated string `graphql:"translatedName,alias:nameTranslated" json:"nameTranslated"`
			} `graphql:"topicTags" json:"topicTags"`
			Status          string   `graphql:"status" json:"status"`
			IsInMyFavorites bool     `graphql:"isFavor,alias:IsInMyFavorites" json:"isInMyFavorites"`
			AcRate          float64  `graphql:"acRate" json:"acRate"`
			Frequency       *float64 `graphql:"freqBar,alias:frequency" json:"frequency"`
		} `graphql:"question" json:"question"`
	} `graphql:"todayRecord" json:"todayRecord"`
}

func (tq *TodayQuestion) QueryName() string {
	return "questionOfTodayV2"
}

type ComboRecord struct {
	TodayRecord []*struct {
		UserStatus string `graphql:"userStatus" json:"userStatus"` // 用户的完成状态(FINISH)
		ComboTimes int    `graphql:"comboTimes" json:"comboTimes"` // 连续完成次数
	} `graphql:"todayRecord" json:"todayRecord"`
}

func (cr *ComboRecord) QueryName() string {
	return "todayRecord"
}

type SubmissionList struct {
	SubmissionList struct {
		LastKey     string `graphql:"lastKey" json:"lastKey"`
		HasNext     bool   `graphql:"hasNext" json:"hasNext"`
		Submissions []*struct {
			Id                string `graphql:"id" json:"id"`
			Title             string `graphql:"title" json:"title"`
			Status            string `graphql:"status" json:"status"`
			StatusDisplay     string `graphql:"statusDisplay" json:"statusDisplay"`
			Lang              string `graphql:"lang" json:"lang"`
			LangName          string `graphql:"langVerboseName,alias:langName" json:"langName"`
			Runtime           string `graphql:"runtime" json:"runtime"`
			Timestamp         string `graphql:"timestamp" json:"timestamp"`
			Url               string `graphql:"url" json:"url"`
			IsPending         string `graphql:"isPending" json:"isPending"`
			Memory            string `graphql:"memory" json:"memory"`
			FrontendId        int    `graphql:"frontendId" json:"frontendId"`
			SubmissionComment struct {
				Comment  string `graphql:"comment" json:"comment"`
				FlagType string `graphql:"flagType" json:"flagType"`
			} `graphql:"submissionComment" json:"submissionComment"`
		} `graphql:"submissions" json:"submissions"`
	} `graphql:"submissionList,var-args" json:"submissionList"`
}

func (sl *SubmissionList) QueryName() string {
	return "submissionList"
}

type SubmissionListVariables struct {
	Offset       int     `graphql:"offset,type:Int,notnull" json:"offset"`
	Limit        int     `graphql:"limit,type:Int,notnull" json:"limit"`
	LastKey      *string `graphql:"lastKey,type:String" json:"lastKey"`
	QuestionSlug *string `graphql:"questionSlug,type:String" json:"questionSlug"`
	Lang         *string `graphql:"lang,type:String" json:"lang"`
	Status       *string `graphql:"status,type:SubmissionStatusEnum" json:"status"`
}

type SubmissionDetail struct {
	SubmissionDetail struct {
		Code            string `graphql:"code" json:"code"`
		Timestamp       int64  `graphql:"timestamp" json:"timestamp"`
		StatusDisplay   string `graphql:"statusDisplay" json:"statusDisplay"`
		IsMine          bool   `graphql:"isMine" json:"isMine"`
		RuntimeDisplay  string `graphql:"runtime,alias:runtimeDisplay" json:"runtimeDisplay"`
		MemoryDisplay   string `graphql:"memory,alias:memoryDisplay" json:"memoryDisplay"`
		Memory          string `graphql:"rawMemory,alias:memory" json:"memory"`
		Lang            string `graphql:"lang" json:"lang"`
		LangVerboseName string `graphql:"langVerboseName,alias:langName" json:"langVerboseName"`
		Question        struct {
			QuestionId         string `graphql:"questionId" json:"questionId"`
			TitleSlug          string `graphql:"titleSlug" json:"titleSlug"`
			HasFrontendPreview bool   `graphql:"hasFrontendPreview" json:"hasFrontendPreview"`
		} `graphql:"question" json:"question"`
		User struct {
			RealName   string `graphql:"realName" json:"realName"`
			UserAvatar string `graphql:"userAvatar" json:"userAvatar"`
			UserSlug   string `graphql:"userSlug" json:"userSlug"`
		} `graphql:"user" json:"user"`
		RuntimePercentile float64 `graphql:"runtimePercentile" json:"runtimePercentile"`
		MemoryPercentile  float64 `graphql:"memoryPercentile" json:"memoryPercentile"`
		SubmissionComment struct {
			FlagType string `graphql:"flagType" json:"flagType"`
		} `graphql:"submissionComment" json:"submissionComment"`
		PassedTestCaseCnt     int    `graphql:"passedTestCaseCnt" json:"passedTestCaseCnt"`
		TotalTestCaseCnt      int    `graphql:"totalTestCaseCnt" json:"totalTestCaseCnt"`
		FullCodeOutput        any    `graphql:"fullCodeOutput" json:"fullCodeOutput"`
		TestDescriptions      any    `graphql:"testDescriptions" json:"testDescriptions"`
		TestInfo              any    `graphql:"testInfo" json:"testInfo"`
		TestBodies            any    `graphql:"testBodies" json:"testBodies"`
		StdOutput             string `graphql:"stdOutput" json:"stdOutput"`
		GeneralSubmissionNode struct {
			OutputDetail struct {
				CodeOutput     string `graphql:"codeOutput" json:"codeOutput"`
				ExpectedOutput string `graphql:"expectedOutput" json:"expectedOutput"`
				Input          string `graphql:"input" json:"input"`
				CompileError   string `graphql:"compileError" json:"compileError"`
				RuntimeError   string `graphql:"runtimeError" json:"runtimeError"`
				LastTestcase   string `graphql:"lastTestcase" json:"lastTestcase"`
			} `graphql:"outputDetail" json:"outputDetail"`
		} `graphql:"GeneralSubmissionNode,inline" json:",inline"`
		ContestSubmissionNode struct {
			OutputDetail struct {
				CodeOutput     string `graphql:"codeOutput" json:"codeOutput"`
				ExpectedOutput string `graphql:"expectedOutput" json:"expectedOutput"`
				Input          string `graphql:"input" json:"input"`
				CompileError   string `graphql:"compileError" json:"compileError"`
				RuntimeError   string `graphql:"runtimeError" json:"runtimeError"`
				LastTestcase   string `graphql:"lastTestcase" json:"lastTestcase"`
			} `graphql:"outputDetail" json:"outputDetail"`
		} `graphql:"ContestSubmissionNode,inline" json:",inline"`
	} `graphql:"submissionDetail,var-args" json:"submissionDetail"`
}

func (sd *SubmissionDetail) QueryName() string {
	return "submissionDetails"
}

type SubmissionDetailVariables struct {
	SubmissionId string `graphql:"submissionId,type:ID!" json:"submissionId"`
}

type QuestionTopics struct {
	QuestionSolutionArticles struct {
		TotalNum int `graphql:"totalNum" json:"totalNum"`
		Edges    []struct {
			Node struct {
				RewardEnabled any    `graphql:"rewardEnabled" json:"rewardEnabled"`
				CanEditReward bool   `graphql:"canEditReward" json:"canEditReward"`
				Uuid          string `graphql:"uuid" json:"uuid"`
				Title         string `graphql:"title" json:"title"`
				Slug          string `graphql:"slug" json:"slug"`
				Sunk          bool   `graphql:"sunk" json:"sunk"`
				ChargeType    string `graphql:"chargeType" json:"chargeType"`
				Status        string `graphql:"status" json:"status"`
				Identifier    string `graphql:"identifier" json:"identifier"`
				CanEdit       bool   `graphql:"canEdit" json:"canEdit"`
				CanSee        bool   `graphql:"canSee" json:"canSee"`
				ReactionType  any    `graphql:"reactionType" json:"reactionType"`
				HasVideo      bool   `graphql:"hasVideo" json:"hasVideo"`
				FavoriteCount int    `graphql:"favoriteCount" json:"favoriteCount"`
				UpvoteCount   int    `graphql:"upvoteCount" json:"upvoteCount"`
				ReactionsV2   []struct {
					Count        int    `graphql:"count" json:"count"`
					ReactionType string `graphql:"reactionType" json:"reactionType"`
				} `graphql:"reactionsV2" json:"reactionsV2"`
				Tags []struct {
					Name           string `graphql:"name" json:"name"`
					NameTranslated string `graphql:"nameTranslated" json:"nameTranslated"`
					Slug           string `graphql:"slug" json:"slug"`
					TagType        string `graphql:"tagType" json:"tagType"`
				} `graphql:"tags" json:"tags"`
				CreatedAt string `graphql:"createdAt" json:"createdAt"`
				Thumbnail string `graphql:"thumbnail" json:"thumbnail"`
				Author    struct {
					Username           string `graphql:"username" json:"username"`
					CertificationLevel string `graphql:"certificationLevel" json:"certificationLevel"`
					Profile            struct {
						UserAvatar string `graphql:"userAvatar" json:"userAvatar"`
						UserSlug   string `graphql:"userSlug" json:"userSlug"`
						RealName   string `graphql:"realName" json:"realName"`
						Reputation int    `graphql:"reputation" json:"reputation"`
					} `graphql:"profile" json:"profile"`
				} `graphql:"author" json:"author"`
				Summary string `graphql:"summary" json:"summary"`
				Topic   struct {
					Id           int  `graphql:"id" json:"id"`
					CommentCount int  `graphql:"commentCount" json:"commentCount"`
					ViewCount    int  `graphql:"viewCount" json:"viewCount"`
					Pinned       bool `graphql:"pinned" json:"pinned"`
				} `graphql:"topic" json:"topic"`
				ByLeetcode    bool `graphql:"byLeetcode" json:"byLeetcode"`
				IsMyFavorite  bool `graphql:"isMyFavorite" json:"isMyFavorite"`
				IsMostPopular bool `graphql:"isMostPopular" json:"isMostPopular"`
				IsEditorsPick bool `graphql:"isEditorsPick" json:"isEditorsPick"`
				HitCount      int  `graphql:"hitCount" json:"hitCount"`
				VideosInfo    []struct {
					VideoId  string `graphql:"videoId" json:"videoId"`
					CoverUrl string `graphql:"coverUrl" json:"coverUrl"`
					Duration any    `graphql:"duration" json:"duration"`
				} `graphql:"videosInfo" json:"videosInfo"`
			} `graphql:"node" json:"node"`
		} `graphql:"edges" json:"edges"`
	} `graphql:"questionSolutionArticles,var-args" json:"questionSolutionArticles"`
}

func (qt *QuestionTopics) QueryName() string {
	return "questionTopicsList"
}

type QuestionTopicsVariables struct {
	QuestionSlug string   `graphql:"questionSlug,type:String,notnull" json:"questionSlug"`
	Skip         int      `graphql:"skip,type:Int" json:"skip"`
	First        int      `graphql:"first,type:Int" json:"first"`
	OrderBy      string   `graphql:"orderBy,type:SolutionArticleOrderBy" json:"orderBy"`
	UserInput    string   `graphql:"userInput,type:String" json:"userInput"`
	TagSlugs     []string `graphql:"tagSlugs,type:String,notnull,array" json:"tagSlugs"`
}

type DiscussTopic struct {
	SolutionArticle struct {
		Content string `graphql:"content" json:"content"`
		Next    struct {
			Slug  string `graphql:"slug" json:"slug"`
			Title string `graphql:"title" json:"title"`
		} `graphql:"next" json:"next"`
		Prev struct {
			Slug  string `graphql:"slug" json:"slug"`
			Title string `graphql:"title" json:"title"`
		} `graphql:"prev" json:"prev"`
	} `graphql:"solutionArticle,var-args,arg:orderBy=DEFAULT" json:"solutionArticle"`
}

func (qt *DiscussTopic) QueryName() string {
	return "discussTopic"
}

type DiscussTopicVariables struct {
	Slug string `graphql:"slug,type:String" json:"slug"`
}

type QuestionDetail struct {
	LanguageList []struct {
		Id          any    `graphql:"id" json:"id"`
		Name        string `graphql:"name" json:"name"`
		VerboseName string `graphql:"verboseName" json:"verboseName"`
	} `graphql:"languageList" json:"languageList"`
	StatusList []struct {
		Id   any    `graphql:"id" json:"id"`
		Name string `graphql:"translatedName,alias=name" json:"name"`
	} `graphql:"statusList" json:"statusList"`
	Question struct {
		Title              string `graphql:"title" json:"title"`
		TitleSlug          string `graphql:"titleSlug" json:"titleSlug"`
		QuestionId         any    `graphql:"questionId" json:"questionId"`
		QuestionFrontendId any    `graphql:"questionFrontendId" json:"questionFrontendId"`
		QuestionTitle      string `graphql:"questionTitle" json:"questionTitle"`
		TranslatedTitle    string `graphql:"translatedTitle" json:"translatedTitle"`
		Content            string `graphql:"content" json:"content"`
		TranslatedContent  string `graphql:"translatedContent" json:"translatedContent"`
		CategoryTitle      string `graphql:"categoryTitle" json:"categoryTitle"`
		Difficulty         string `graphql:"difficulty" json:"difficulty"`
		Stats              string `graphql:"stats" json:"stats"`
		Style              any    `graphql:"style" json:"style"`
		Contributors       []struct {
			Username   string `graphql:"username" json:"username"`
			ProfileUrl string `graphql:"profileUrl" json:"profileUrl"`
			AvatarUrl  string `graphql:"avatarUrl" json:"avatarUrl"`
		} `graphql:"contributors" json:"contributors"`
		Book *struct {
			Id               any    `graphql:"id" json:"id"`
			BookName         string `graphql:"bookName" json:"bookName"`
			PressName        string `graphql:"pressName" json:"pressName"`
			Source           string `graphql:"source" json:"source"`
			ShortDescription string `graphql:"shortDescription" json:"shortDescription"`
			FullDescription  string `graphql:"fullDescription" json:"fullDescription"`
			BookImgUrl       string `graphql:"bookImgUrl" json:"bookImgUrl"`
			PressImgUrl      string `graphql:"pressImgUrl" json:"pressImgUrl"`
			ProductUrl       string `graphql:"productUrl" json:"productUrl"`
		} `graphql:"book" json:"book"`
		CompanyTagStatsV2 any `graphql:"companyTagStatsV2" json:"companyTagStatsV2"`
		TopicTags         []struct {
			Name           string `graphql:"name" json:"name"`
			Slug           string `graphql:"slug" json:"slug"`
			TranslatedName string `graphql:"translatedName" json:"translatedName"`
		} `graphql:"topicTags" json:"topicTags"`
		SimilarQuestions   string   `graphql:"similarQuestions" json:"similarQuestions"`
		MysqlSchemas       any      `graphql:"mysqlSchemas" json:"mysqlSchemas"`
		DataSchemas        any      `graphql:"dataSchemas" json:"dataSchemas"`
		FrontendPreviews   any      `graphql:"frontendPreviews" json:"frontendPreviews"`
		Likes              int      `graphql:"likes" json:"likes"`
		Dislikes           int      `graphql:"dislikes" json:"dislikes"`
		IsPaidOnly         bool     `graphql:"isPaidOnly" json:"isPaidOnly"`
		Status             string   `graphql:"status" json:"status"`
		BoundTopicId       any      `graphql:"boundTopicId" json:"boundTopicId"`
		EnableTestMode     bool     `graphql:"enableTestMode" json:"enableTestMode"`
		MetaData           string   `graphql:"metaData" json:"metaData"`
		EnableRunCode      bool     `graphql:"enableRunCode" json:"enableRunCode"`
		EnableSubmit       bool     `graphql:"enableSubmit" json:"enableSubmit"`
		EnvInfo            string   `graphql:"envInfo" json:"envInfo"`
		IsLiked            bool     `graphql:"isLiked" json:"isLiked"`
		NextChallengePairs any      `graphql:"nextChallengePairs" json:"nextChallengePairs"`
		LibraryUrl         string   `graphql:"libraryUrl" json:"libraryUrl"`
		Hints              []string `graphql:"hints" json:"hints"`
		CodeSnippets       []struct {
			Code     string `graphql:"code" json:"code"`
			Lang     string `graphql:"lang" json:"lang"`
			LangSlug string `graphql:"langSlug" json:"langSlug"`
		} `graphql:"codeSnippets" json:"codeSnippets"`
		JsonExampleTestcases string `graphql:"jsonExampleTestcases" json:"jsonExampleTestcases"`
		ExampleTestcases     string `graphql:"exampleTestcases" json:"exampleTestcases"`
		SampleTestCase       string `graphql:"sampleTestCase" json:"sampleTestCase"`
		HasFrontendPreview   bool   `graphql:"hasFrontendPreview" json:"hasFrontendPreview"`
		EditorType           string `graphql:"editorType" json:"editorType"`
		FeaturedContests     []struct {
			TitleSlug string `graphql:"titleSlug" json:"titleSlug"`
			TitleCn   string `graphql:"titleCn" json:"titleCn"`
			Title     string `graphql:"title" json:"title"`
		} `graphql:"featuredContests" json:"featuredContests"`
	} `graphql:"question,var-args" json:"question"`
}

func (qd *QuestionDetail) QueryName() string {
	return "questionDetail"
}

type QuestionDetailVariables struct {
	TitleSlug string `graphql:"titleSlug,type:String,notnull" json:"titleSlug"`
}
