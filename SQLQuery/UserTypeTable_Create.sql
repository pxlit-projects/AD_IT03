    IF NOT EXISTS (SELECT * FROM sys.objects 
        WHERE object_id = OBJECT_ID(N'[dbo].[UserType]') 
        AND type in (N'U'))
    BEGIN
    CREATE TABLE [dbo].[UserType](
        [id] [int] NOT NULL,
		[screenname] [nvarchar](50) NOT NULL,
		[description] [nvarchar](100) NOT NULL,
     CONSTRAINT [PK_UserType] PRIMARY KEY CLUSTERED   
    (
    [id] ASC
    )WITH (IGNORE_DUP_KEY = OFF)
    ) 
    END;
    GO