    IF NOT EXISTS (SELECT * FROM sys.objects 
        WHERE object_id = OBJECT_ID(N'[dbo].[User]') 
        AND type in (N'U'))
    BEGIN
    CREATE TABLE [dbo].[User](
        [id] [int] NOT NULL,
        [firstname] [nvarchar](50) NOT NULL,
        [lastname] [nvarchar](50) NOT NULL,
		[screenname] [nvarchar](50) NOT NULL,
		[password] [nvarchar](50) NOT NULL,
		[email] [nvarchar](100) NOT NULL,
		[type] [int] NOT NULL
     CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED   
    (
    [id] ASC
    )WITH (IGNORE_DUP_KEY = OFF)
    ) 
    END;
    GO