BEGIN
	IF EXISTS (
		SELECT * FROM sys.objects 
		WHERE object_id = OBJECT_ID(N'[dbo].[UserType]') 
		AND type in (N'U')) 
		DROP TABLE [dbo].[UserType]
	
CREATE TABLE [dbo].[UserType](
    [id] [int] NOT NULL IDENTITY(1,1),
	[screenname] [nvarchar](3) NOT NULL,
	[description] [nvarchar](100) NOT NULL,
    CONSTRAINT [PK_UserType] PRIMARY KEY CLUSTERED   
(
[id] ASC
)WITH (IGNORE_DUP_KEY = OFF)
) 
END;
GO